package br.com.bot.application.config

import br.com.bot.domain.market.MarketSymbol
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "bot")
data class BotProperties(
    val symbols: List<MarketSymbol>,
    val tickIntervalMs: Long
)
