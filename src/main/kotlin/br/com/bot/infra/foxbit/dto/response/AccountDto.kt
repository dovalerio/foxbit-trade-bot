package br.com.bot.infra.foxbit.dto.response

data class AccountDto(
    val currency_symbol: String?,
    val balance: String?,
    val locked: String?
)
