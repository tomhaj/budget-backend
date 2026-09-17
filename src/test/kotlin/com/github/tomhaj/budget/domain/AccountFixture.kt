package com.github.tomhaj.budget.domain

import java.math.BigDecimal

fun aAccount(
    name: String = "example account",
    onBudget: Boolean = true,
    openingBalance: String = "0.00",
) = Account.open(Name(name), onBudget, Money.of(BigDecimal(openingBalance)))
