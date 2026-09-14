package com.github.tomhaj.budget.application

import java.math.BigDecimal

class GetAccountsQueryHandler {
    fun handle(query: GetAccountsQuery): List<Account> = listOf()
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
