package com.github.tomhaj.budget.application

import com.github.tomhaj.budget.domain.Account
import com.github.tomhaj.budget.domain.Either
import com.github.tomhaj.budget.domain.Money
import com.github.tomhaj.budget.domain.Name
import java.math.BigDecimal

class OpenAccountCommandHandler(
    private val transaction: PersistenceTransaction,
    private val accountRepository: AccountRepository,
) {
    fun handle(command: OpenAccountCommand): Either<Unit, Unit> =
        transaction.execute {
            accountRepository.save(command.toAccount())
            Either.Success(Unit)
        }
}

data class OpenAccountCommand(
    val name: String,
    val onBudget: Boolean,
    val openingBalance: BigDecimal,
) {
    fun toAccount() = Account.open(Name(name), onBudget, Money(openingBalance))
}
