package br.com.bot.application.service

import br.com.bot.application.gateway.FoxbitTradingGateway
import br.com.bot.domain.engine.TradingContext
import br.com.bot.domain.market.MarketSymbol
import br.com.bot.domain.order.toIntent
import br.com.bot.domain.risk.RiskManager
import br.com.bot.domain.risk.RiskResult
import br.com.bot.domain.strategy.TradingStrategy
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Service
class TradingCycleService(

    private val gateway: FoxbitTradingGateway,
    private val strategy: TradingStrategy,
    private val risk: RiskManager

) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun runCycle(symbol: MarketSymbol) {

        log.info("[{}] Observando mercado", symbol)

        Mono.zip(
            gateway.getTicker(symbol),
            gateway.getBalance(symbol.quote)
        )
            .flatMap { tuple ->

                val context = TradingContext(
                    market = symbol,
                    ticker = tuple.t1,
                    balance = tuple.t2
                )

                log.info(
                    "[{}] Preco ultimo={} compra={} venda={}",
                    symbol,
                    context.ticker.lastPrice,
                    context.ticker.bestBid,
                    context.ticker.bestAsk
                )

                strategy.decide(context)
                    .flatMap { decision ->

                        val intent = decision.toIntent(context, BigDecimal("0.0001"))

                        if (intent == null) {
                            log.info("[{}] Nenhuma oportunidade", symbol)
                            return@flatMap Mono.empty()
                        }

                        log.info("[{}] Estrategia quer {}", symbol, intent)

                        risk.evaluate(context, intent)
                            .flatMap { result ->
                                when (result) {
                                    is RiskResult.Approved -> {
                                        log.warn(
                                            "[{}] Ordem aprovada simulando envio {}",
                                            symbol,
                                            intent
                                        )
                                        Mono.empty()
                                    }

                                    is RiskResult.Rejected -> {
                                        log.warn(
                                            "[{}] Ordem bloqueada {}",
                                            symbol,
                                            result.reason
                                        )
                                        Mono.empty()
                                    }
                                }
                            }
                    }
            }
            .doOnError { ex ->
                log.error("[{}] Erro ciclo {}", symbol, ex.rootMessage())
            }
            .doFinally {
                log.info("[{}] Ciclo finalizado", symbol)
            }
            .subscribe()
    }

    fun Throwable.rootMessage(): String =
        generateSequence(this) { it.cause }
            .last()
            .message ?: this.message ?: "erro desconhecido"
}
