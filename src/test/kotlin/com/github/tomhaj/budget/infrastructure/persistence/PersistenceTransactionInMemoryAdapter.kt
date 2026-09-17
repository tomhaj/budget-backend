package com.github.tomhaj.budget.infrastructure.persistence

import com.github.tomhaj.budget.application.PersistenceTransaction
import com.github.tomhaj.budget.domain.Either

class PersistenceTransactionInMemoryAdapter : PersistenceTransaction {
    override fun <F, S> execute(action: () -> Either<F, S>): Either<F, S> = action()

    override fun <T> executeReadOnly(action: () -> T): T = action()
}
