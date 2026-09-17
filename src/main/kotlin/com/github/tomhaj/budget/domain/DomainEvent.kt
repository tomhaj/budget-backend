package com.github.tomhaj.budget.domain

import java.time.Instant

sealed interface DomainEvent {
    val occurredAt: Instant
}
