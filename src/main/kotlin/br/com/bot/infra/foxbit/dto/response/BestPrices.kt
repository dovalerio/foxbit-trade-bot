package br.com.bot.infra.foxbit.dto.response

data class BestPrices(
    val ask: OrderBookLevel,
    val bid: OrderBookLevel
)
