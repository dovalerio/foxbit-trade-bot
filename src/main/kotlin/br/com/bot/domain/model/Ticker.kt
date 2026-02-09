package br.com.bot.domain.model

import br.com.bot.domain.market.MarketSymbol
import java.math.BigDecimal

data class Ticker(
    val market: MarketSymbol,
    val lastPrice: BigDecimal,
    val bestBid: BigDecimal,
    val bestAsk: BigDecimal
)
