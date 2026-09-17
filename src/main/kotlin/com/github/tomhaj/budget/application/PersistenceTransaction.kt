package com.github.tomhaj.budget.application

import com.github.tomhaj.budget.domain.Either

interface PersistenceTransaction {
    fun <F, S> execute(action: () -> Either<F, S>): Either<F, S>

    fun <T> executeReadOnly(action: () -> T): T
}
