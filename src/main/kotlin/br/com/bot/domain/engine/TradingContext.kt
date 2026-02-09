package br.com.bot.domain.engine

import br.com.bot.domain.market.MarketSymbol
import br.com.bot.domain.model.Balance
import br.com.bot.domain.model.Ticker

data class TradingContext(
    val market: MarketSymbol,
    val ticker: Ticker,
    val balance: Balance
)
