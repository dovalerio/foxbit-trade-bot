package br.com.bot.infra.foxbit.dto.request

import java.math.BigDecimal

data class CreateOrderRequest(
    val market_symbol: String,
    val side: String,
    val type: String,
    val quantity: BigDecimal,
    val price: BigDecimal? = null,
    val client_order_id: String? = null
)
