package com.github.tomhaj.budget.domain

sealed interface Either<out F, out S> {
    data class Failure<out F>(
        val error: F,
    ) : Either<F, Nothing>

    data class Success<out S>(
        val value: S,
    ) : Either<Nothing, S>
}

inline fun <F, S, T> Either<F, S>.map(transform: (S) -> T): Either<F, T> =
    when (this) {
        is Either.Success -> Either.Success(transform(value))
        is Either.Failure -> this
    }

inline fun <F, S, T> Either<F, S>.flatMap(next: (S) -> Either<F, T>): Either<F, T> =
    when (this) {
        is Either.Success -> next(value)
        is Either.Failure -> this
    }

inline fun <F, G, S> Either<F, S>.mapFailure(transform: (F) -> G): Either<G, S> =
    when (this) {
        is Either.Success -> this
        is Either.Failure -> Either.Failure(transform(error))
    }
