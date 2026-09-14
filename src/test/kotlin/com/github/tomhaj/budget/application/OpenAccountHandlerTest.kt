package com.github.tomhaj.budget.application

import org.junit.jupiter.api.Test

class OpenAccountHandlerTest : TestingFixture() {
    @Test
    fun `should open accounts`() {
        // when
        openAccount(name = "My first account", onBudget = true, openingBalance = "0.00")
        openAccount(name = "My second account", onBudget = false, openingBalance = "3654.50")

        // then
        assertAccounts {
            hasCountOfAccounts(2)
            hasUniqueAccountsIds()
            hasAccountThat {
                hasName("My first account")
                hasOnBudget(true)
                hasBalance("0.00")
            }
            hasAccountThat {
                hasName("My second account")
                hasOnBudget(false)
                hasBalance("3654.50")
            }
        }
    }
}
