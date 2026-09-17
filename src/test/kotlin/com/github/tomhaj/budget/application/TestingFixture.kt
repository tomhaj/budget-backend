package com.github.tomhaj.budget.application

import com.github.tomhaj.budget.infrastructure.persistence.AccountRepositoryInMemoryAdapter
import com.github.tomhaj.budget.infrastructure.persistence.PersistenceTransactionInMemoryAdapter
import java.math.BigDecimal

open class TestingFixture {
    private val persistenceTransaction = PersistenceTransactionInMemoryAdapter()
    private val accountRepository = AccountRepositoryInMemoryAdapter()
    private val openAccountCommandHandler = OpenAccountCommandHandler(persistenceTransaction, accountRepository)
    private val getAccountsQueryHandler = GetAccountsQueryHandler(persistenceTransaction, accountRepository)

    protected fun openAccount(
        name: String = "test account",
        onBudget: Boolean = true,
        openingBalance: String = "0.00",
    ) = openAccountCommandHandler.handle(OpenAccountCommand(name, onBudget, BigDecimal(openingBalance)))

    protected fun assertAccounts(
        visibility: Visibility = Visibility.OPEN,
        assertions: AccountsAssert.() -> Unit,
    ): Unit = AccountsAssert(getAccountsQueryHandler.handle(GetAccountsQuery(visibility))).assertions()
}
