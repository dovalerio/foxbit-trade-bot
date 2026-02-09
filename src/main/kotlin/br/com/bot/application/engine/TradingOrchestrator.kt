package br.com.bot.application.engine

import br.com.bot.application.gateway.FoxbitTradingGateway
import br.com.bot.application.service.OrderExecutionService
import br.com.bot.domain.engine.TradingContext
import br.com.bot.domain.market.MarketSymbol
import br.com.bot.domain.order.toIntent
import br.com.bot.domain.risk.RiskManager
import br.com.bot.domain.risk.RiskResult
import br.com.bot.domain.strategy.TradingStrategy
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Component
class TradingOrchestrator(
    private val gateway: FoxbitTradingGateway,
    private val strategy: TradingStrategy,
    private val risk: RiskManager,
    private val executor: OrderExecutionService
) {

    fun run(symbol: MarketSymbol): Mono<Void> {

        return Mono.zip(
            gateway.getTicker(symbol),
            gateway.getBalance(symbol.quote)
        )
            .flatMap { tuple ->

                val context = TradingContext(
                    market = symbol,
                    ticker = tuple.t1,
                    balance = tuple.t2
                )

                strategy.decide(context)
                    .flatMap { decision ->

                        val intent = decision.toIntent(context, BigDecimal("0.0001"))
                            ?: return@flatMap Mono.empty()

                        risk.evaluate(context, intent)
                            .flatMap { result ->
                                when (result) {
                                    is RiskResult.Approved -> executor.execute(intent)
                                    is RiskResult.Rejected -> Mono.empty()
                                }
                            }
                    }
            }
            .then()
    }
}
