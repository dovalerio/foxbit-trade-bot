package br.com.bot.domain.model

import br.com.bot.domain.market.Currency
import java.math.BigDecimal

data class Balance(
    val total: Money,
    val locked: Money
) {
    val available: Money
        get() = Money(total.currency, total.amount - locked.amount)
    companion object {
        fun zero(currency: Currency) =
            Balance(
                total = Money(currency, BigDecimal.ZERO),
                locked = Money(currency, BigDecimal.ZERO)
            )
    }
}