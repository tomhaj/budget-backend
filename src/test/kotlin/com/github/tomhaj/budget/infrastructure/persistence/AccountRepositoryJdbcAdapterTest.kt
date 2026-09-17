package com.github.tomhaj.budget.infrastructure.persistence

import com.github.tomhaj.budget.IntegrationTest
import com.github.tomhaj.budget.application.AccountRepository
import com.github.tomhaj.budget.application.Visibility
import com.github.tomhaj.budget.domain.Name
import com.github.tomhaj.budget.domain.aAccount
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal
import kotlin.test.Test

class AccountRepositoryJdbcAdapterTest : IntegrationTest() {
    @Autowired
    private lateinit var repository: AccountRepository

    @Test
    fun `insert and retrieve all accounts`() {
        // given
        repository.save(aAccount(name = "second account", onBudget = true, openingBalance = "1025.98"))
        repository.save(aAccount(name = "first account", onBudget = true, openingBalance = "250.80"))

        // when
        val subject = repository.findAll(Visibility.OPEN)

        // then
        assertThat(subject).satisfiesExactly(
            {
                assertThat(it.name).isEqualTo(Name("first account"))
                assertThat(it.onBudget).isTrue()
                assertThat(it.workingBalance.value).isEqualByComparingTo(BigDecimal("250.80"))
            },
            {
                assertThat(it.name).isEqualTo(Name("second account"))
                assertThat(it.onBudget).isTrue()
                assertThat(it.workingBalance.value).isEqualByComparingTo(BigDecimal("1025.98"))
            },
        )
    }
}
