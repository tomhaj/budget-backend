package com.github.tomhaj.budget.application

import java.math.BigDecimal
import java.util.UUID

class GetAccountsQueryHandler {
    fun handle(query: GetAccountsQuery): List<Account> =
        listOf(
            Account(UUID.randomUUID().toString(), "My first account", true, BigDecimal("0.00")),
            Account(UUID.randomUUID().toString(), "My second account", false, BigDecimal("3654.50")),
        )
}

data class GetAccountsQuery(
    val visibility: Visibility,
)

data class Account(
    val id: String,
    val name: String,
    val onBudget: Boolean,
    val balance: BigDecimal,
)
