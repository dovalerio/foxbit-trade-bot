package br.com.bot.domain.model

import br.com.bot.domain.market.Currency
import java.math.BigDecimal

data class Money(
    val currency: Currency,
    val amount: BigDecimal
)
