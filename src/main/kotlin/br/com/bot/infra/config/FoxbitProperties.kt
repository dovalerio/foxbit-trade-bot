package br.com.bot.infra.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "foxbit")
data class FoxbitProperties(
    val baseUrl: String,
    val apiKey: String,
    val apiSecret: String
)
