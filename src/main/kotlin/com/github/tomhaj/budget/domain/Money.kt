package com.github.tomhaj.budget.domain

import java.math.BigDecimal
import java.math.RoundingMode.HALF_UP

@JvmInline
value class Money(
    val value: BigDecimal,
) {
    operator fun plus(other: Money): Money = Money(value + other.value)

    operator fun minus(other: Money): Money = Money(value - other.value)

    operator fun compareTo(other: Money): Int = value.compareTo(other.value)

    companion object {
        fun zero() = Money(BigDecimal.ZERO.setScale(2))

        fun of(value: BigDecimal) = Money(value.setScale(2, HALF_UP))
    }
}
