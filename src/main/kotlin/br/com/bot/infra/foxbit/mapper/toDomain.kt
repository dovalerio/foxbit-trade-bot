package br.com.bot.infra.foxbit.mapper

import br.com.bot.domain.market.Currency
import br.com.bot.domain.market.MarketSymbol
import br.com.bot.domain.model.Balance
import br.com.bot.domain.model.Money
import br.com.bot.domain.model.Ticker
import br.com.bot.infra.foxbit.dto.response.AccountsResponse
import br.com.bot.infra.foxbit.dto.response.Ticker24hResponse
import java.math.BigDecimal
fun Ticker24hResponse.toDomain(symbol: MarketSymbol): Ticker {

    val item = data.firstOrNull()
        ?: error("Ticker vazio")

    val last = item.last_trade.price.toBigDecimalOrNull()
        ?: error("Ticker sem last trade price")

    val bid = item.best.bid.price.toBigDecimalOrNull()
        ?: error("Ticker sem best bid")

    val ask = item.best.ask.price.toBigDecimalOrNull()
        ?: error("Ticker sem best ask")

    return Ticker(
        market = symbol,
        lastPrice = last,
        bestBid = bid,
        bestAsk = ask
    )
}

fun AccountsResponse.toDomain(): Map<Currency, Balance> =
    data
        .mapNotNull { dto ->

            val symbol = dto.currency_symbol ?: return@mapNotNull null

            val currency = try {
                Currency.fromApi(symbol)
            } catch (_: Exception) {
                return@mapNotNull null
            }

            val total = dto.balance?.toBigDecimalOrNull() ?: BigDecimal.ZERO
            val locked = dto.locked?.toBigDecimalOrNull() ?: BigDecimal.ZERO

            currency to Balance(
                total = Money(currency, total),
                locked = Money(currency, locked)
            )
        }
        .toMap()

