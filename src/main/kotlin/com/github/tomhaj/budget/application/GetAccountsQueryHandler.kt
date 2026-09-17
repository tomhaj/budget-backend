package com.github.tomhaj.budget.application

import java.math.BigDecimal

class GetAccountsQueryHandler(
    private val transaction: PersistenceTransaction,
    private val accountRepository: AccountRepository,
) {
    fun handle(query: GetAccountsQuery): List<Account> =
        transaction.executeReadOnly {
            accountRepository
                .findAll(query.visibility)
                .map {
                    Account(
                        it.id.value.toString(),
                        it.name.value,
                        it.onBudget,
                        it.workingBalance.value,
                    )
                }
        }
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
