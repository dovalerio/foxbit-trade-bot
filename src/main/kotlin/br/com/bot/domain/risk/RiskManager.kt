package br.com.bot.domain.risk

import br.com.bot.domain.engine.TradingContext
import br.com.bot.domain.order.OrderIntent
import reactor.core.publisher.Mono

interface RiskManager {

    fun evaluate(
        context: TradingContext,
        intent: OrderIntent
    ): Mono<RiskResult>
}
