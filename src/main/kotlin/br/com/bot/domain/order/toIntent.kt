package br.com.bot.domain.order

import Br.com.bot.domain.order.OrderType
import br.com.bot.domain.engine.TradingContext
import br.com.bot.domain.strategy.StrategyDecision
import java.math.BigDecimal

fun StrategyDecision.toIntent(
    context: TradingContext,
    quantity: BigDecimal
): OrderIntent? =
    when (this) {
        StrategyDecision.BUY -> OrderIntent(
            market = context.market,
            side = OrderSide.BUY,
            type = OrderType.MARKET,
            quantity = quantity,
            price = null
        )

        StrategyDecision.SELL -> OrderIntent(
            market = context.market,
            side = OrderSide.SELL,
            type = OrderType.MARKET,
            quantity = quantity,
            price = null
        )

        StrategyDecision.HOLD -> null
    }
