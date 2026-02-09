package br.com.bot.domain.order

import br.com.bot.domain.market.MarketSymbol
import Br.com.bot.domain.order.OrderType
import java.math.BigDecimal

data class OrderIntent(
    val market: MarketSymbol,
    val side: OrderSide,
    val type: OrderType,
    val quantity: BigDecimal,
    val price: BigDecimal? = null
)
