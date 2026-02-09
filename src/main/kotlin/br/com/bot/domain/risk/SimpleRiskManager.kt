package br.com.bot.domain.risk

import br.com.bot.application.gateway.FoxbitTradingGateway
import br.com.bot.domain.engine.TradingContext
import br.com.bot.domain.order.OrderIntent
import br.com.bot.domain.order.OrderSide
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class SimpleRiskManager(
    private val gateway: FoxbitTradingGateway
) : RiskManager {

    override fun evaluate(
        context: TradingContext,
        intent: OrderIntent
    ): Mono<RiskResult> {

        val currency = when (intent.side) {
            OrderSide.BUY -> intent.market.quote
            OrderSide.SELL -> intent.market.base
        }

        val required = when (intent.side) {
            OrderSide.BUY -> {
                val price = intent.price ?: context.ticker.bestAsk
                intent.quantity.multiply(price)
            }
            OrderSide.SELL -> intent.quantity
        }

        return gateway
            .getBalance(currency)
            .validateRequiredAmount(required)
    }
}
