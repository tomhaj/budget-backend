package com.github.tomhaj.budget.infrastructure.persistence

import com.github.tomhaj.budget.application.AccountRepository
import com.github.tomhaj.budget.application.Visibility
import com.github.tomhaj.budget.domain.Account

class AccountRepositoryInMemoryAdapter : AccountRepository {
    private val accounts = mutableListOf<Account>()

    override fun save(account: Account) {
        accounts.add(account)
    }

    override fun findAll(visibility: Visibility): List<Account> = accounts.toList()
}
