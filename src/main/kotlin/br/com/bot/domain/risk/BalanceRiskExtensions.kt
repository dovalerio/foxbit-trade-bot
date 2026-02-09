package br.com.bot.domain.risk

import br.com.bot.domain.math.isGreaterOrEqual
import br.com.bot.domain.model.Balance
import reactor.core.publisher.Mono
import java.math.BigDecimal

fun Mono<Balance>.validateRequiredAmount(
    required: BigDecimal
): Mono<RiskResult> =
    map { balance ->
        val available = balance.available.amount

        if (available.isGreaterOrEqual(required)) {
            RiskResult.Approved
        } else {
            RiskResult.Rejected("Insufficient balance")
        }
    }
