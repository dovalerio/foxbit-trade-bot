package br.com.bot.domain.risk

sealed class RiskResult {

    data object Approved : RiskResult()

    data class Rejected(
        val reason: String
    ) : RiskResult()
}
