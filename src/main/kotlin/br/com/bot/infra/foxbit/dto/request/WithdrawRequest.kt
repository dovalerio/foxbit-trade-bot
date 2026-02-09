package br.com.bot.infra.foxbit.dto.request

import java.math.BigDecimal

data class WithdrawRequest(
    val currency_symbol: String,
    val amount: BigDecimal,
    val address: String,
    val memo: String? = null
)
