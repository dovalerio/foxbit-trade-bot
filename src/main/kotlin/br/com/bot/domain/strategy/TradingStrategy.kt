package br.com.bot.domain.strategy

import br.com.bot.domain.engine.TradingContext
import reactor.core.publisher.Mono

interface TradingStrategy {

    fun decide(context: TradingContext): Mono<StrategyDecision>
}
