package br.com.bot.application.scheduler

import br.com.bot.application.config.BotProperties
import br.com.bot.application.service.TradingCycleService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BotScheduler(
    private val properties: BotProperties,
    private val tradingCycleService: TradingCycleService
) {

    @Scheduled(fixedDelayString = "\${bot.tick-interval-ms}")
    fun execute() {

        properties.symbols.forEach { symbol ->
            tradingCycleService.runCycle(symbol)
        }
    }
}
