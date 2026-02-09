package br.com.bot.infra.foxbit.dto.response

import Br.com.bot.infra.foxbit.dto.response.LastTrade

data class TickerItem(
    val market_symbol: String,
    val last_trade: LastTrade,
    val best: BestPrices
)
