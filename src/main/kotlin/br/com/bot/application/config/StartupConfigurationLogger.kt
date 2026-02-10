package br.com.bot.application.config


import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class StartupConfigurationLogger(
    private val properties: BotProperties
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostConstruct
    fun log() {
        log.info("========================================")
        log.info("Bot iniciado com a seguinte configuracao")
        log.info("Simbolos {}", properties.symbols)
        log.info("Buy below {}", properties.strategy.buyBelow)
        log.info("Sell above {}", properties.strategy.sellAbove)
        log.info("Tick interval {} ms", properties.tickIntervalMs)
        log.info("========================================")
    }
}
