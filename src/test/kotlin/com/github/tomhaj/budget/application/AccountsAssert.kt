package com.github.tomhaj.budget.application

import org.assertj.core.api.AbstractAssert
import java.math.BigDecimal

class AccountsAssert(
    actual: List<Account>,
) : AbstractAssert<AccountsAssert, List<Account>>(actual, AccountsAssert::class.java) {
    fun hasCountOfAccounts(expected: Int): AccountsAssert {
        isNotNull

        if (actual.size != expected) {
            failWithMessage("Expected to have <$expected> accounts but was <${actual.size}>")
        }

        return this
    }

    fun hasUniqueAccountsIds(): AccountsAssert {
        isNotNull

        val allIds = actual.map { it.id }
        val duplicates =
            allIds
                .groupingBy { it }
                .eachCount()
                .filterValues { it > 1 }
                .keys

        if (duplicates.isNotEmpty()) {
            failWithMessage(
                "Expected all accounts ids to be unique but found duplicates <%s>",
                duplicates,
            )
        }

        return this
    }

    fun hasAccountThat(assertions: AccountAssert.() -> Unit): AccountsAssert {
        isNotNull

        val matches =
            actual.any { account ->
                try {
                    AccountAssert(account).assertions()
                    true
                } catch (_: AssertionError) {
                    false
                }
            }

        if (!matches) {
            failWithMessage("Accounts not found")
        }

        return this
    }
}

class AccountAssert(
    actual: Account,
) : AbstractAssert<AccountAssert, Account>(actual, AccountAssert::class.java) {
    fun hasName(expected: String): AccountAssert {
        isNotNull

        if (actual.name != expected) {
            failWithMessage("Expected to have <$expected> name but was <${actual.name}>")
        }

        return this
    }

    fun hasOnBudget(expected: Boolean): AccountAssert {
        isNotNull

        if (actual.onBudget != expected) {
            failWithMessage("Expected to have on budget <$expected> but was <${actual.onBudget}>")
        }

        return this
    }

    fun hasBalance(expected: String): AccountAssert {
        isNotNull

        if (actual.balance.compareTo(BigDecimal(expected)) != 0) {
            failWithMessage("Expected to have <$expected> but was <${actual.balance}>")
        }

        return this
    }
}
