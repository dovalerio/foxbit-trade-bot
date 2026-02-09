package br.com.bot.domain.math

import java.math.BigDecimal

fun BigDecimal.isGreaterThan(other: BigDecimal) =
    this.compareTo(other) > 0

fun BigDecimal.isLessThan(other: BigDecimal) =
    this.compareTo(other) < 0

fun BigDecimal.isGreaterOrEqual(other: BigDecimal) =
    this.compareTo(other) >= 0

fun BigDecimal.isLessOrEqual(other: BigDecimal) =
    this.compareTo(other) <= 0
