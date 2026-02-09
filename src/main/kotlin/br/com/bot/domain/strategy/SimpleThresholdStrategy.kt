package br.com.bot.domain.strategy

import br.com.bot.domain.engine.TradingContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Component
class SimpleThresholdStrategy(

    @Value("\${bot.strategy.buy-below}")
    private val buyBelow: BigDecimal,

    @Value("\${bot.strategy.sell-above}")
    private val sellAbove: BigDecimal

) : TradingStrategy {

    override fun decide(context: TradingContext): Mono<StrategyDecision> {

        val price = context.ticker.lastPrice

        val decision = when {
            price.compareTo(buyBelow) < 0 -> StrategyDecision.BUY
            price.compareTo(sellAbove) > 0 -> StrategyDecision.SELL
            else -> StrategyDecision.HOLD
        }

        return Mono.just(decision)
    }
}
