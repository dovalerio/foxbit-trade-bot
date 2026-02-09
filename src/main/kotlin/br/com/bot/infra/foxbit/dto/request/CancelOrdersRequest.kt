package br.com.bot.infra.foxbit.dto.request

data class CancelOrdersRequest(
    val market_symbol: String? = null,
    val side: String? = null,
    val type: String? = null
)
