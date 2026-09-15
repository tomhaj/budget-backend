package com.github.tomhaj.budget.application

import com.github.tomhaj.budget.domain.Account

interface AccountRepository {
    fun save(account: Account)

    fun findAll(visibility: Visibility): List<Account>
}

enum class Visibility {
    OPEN,
}
