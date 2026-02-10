package br.com.bot.application.config

import java.math.BigDecimal

data class StrategyProperties(
    val buyBelow: BigDecimal,
    val sellAbove: BigDecimal,
    val minQty: BigDecimal,
    val maxQty: BigDecimal
)
