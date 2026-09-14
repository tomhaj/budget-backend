package com.github.tomhaj.budget.application

import com.github.tomhaj.budget.domain.Account
import com.github.tomhaj.budget.domain.Either
import com.github.tomhaj.budget.domain.Money
import java.math.BigDecimal

class OpenAccountCommandHandler {
    fun handle(command: OpenAccountCommand): Either<Unit, Unit> = Either.Success(Unit)
}

data class OpenAccountCommand(
    val name: String,
    val onBudget: Boolean,
    val openingBalance: BigDecimal,
) {
    fun toAccount() = Account.open(name, onBudget, Money(openingBalance))
}
