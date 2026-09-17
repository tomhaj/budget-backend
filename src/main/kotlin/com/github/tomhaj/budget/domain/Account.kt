package com.github.tomhaj.budget.domain

import java.util.UUID

class Account private constructor(
    val id: AccountId,
    val name: Name,
    val onBudget: Boolean,
    private val balance: Money,
) {
    val workingBalance: Money get() = balance

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Account) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    companion object {
        fun open(
            name: Name,
            onBudget: Boolean,
            openingBalance: Money,
        ) = Account(AccountId.nextValue(), name, onBudget, openingBalance)

        fun restore(
            id: AccountId,
            name: Name,
            onBudget: Boolean,
            balance: Money,
        ) = Account(id, name, onBudget, balance)
    }
}

@JvmInline
value class AccountId(
    val value: UUID,
) {
    companion object {
        fun nextValue(): AccountId = AccountId(UUID.randomUUID())
    }
}

@JvmInline
value class Name(
    val value: String,
)
