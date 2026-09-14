package com.github.tomhaj.budget.application

import java.math.BigDecimal

open class TestingFixture {
    private val openAccountCommandHandler = OpenAccountCommandHandler()
    private val getAccountsQueryHandler = GetAccountsQueryHandler()

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
