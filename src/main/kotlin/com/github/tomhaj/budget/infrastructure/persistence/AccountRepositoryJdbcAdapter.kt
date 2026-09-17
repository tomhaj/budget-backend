package com.github.tomhaj.budget.infrastructure.persistence

import com.github.tomhaj.budget.application.AccountRepository
import com.github.tomhaj.budget.application.Visibility
import com.github.tomhaj.budget.domain.Account
import com.github.tomhaj.budget.domain.AccountId
import com.github.tomhaj.budget.domain.Money
import com.github.tomhaj.budget.domain.Name
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.UUID

class AccountRepositoryJdbcAdapter(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : AccountRepository {
    override fun save(account: Account) {
        jdbcTemplate.update(
            "INSERT INTO account (id, name, on_budget, balance) VALUES (:id, :name, :onBudget, :balance)",
            mapOf(
                "id" to account.id.value,
                "name" to account.name.value,
                "onBudget" to account.onBudget,
                "balance" to account.workingBalance.value,
            ),
        )
    }

    override fun findAll(visibility: Visibility): List<Account> =
        jdbcTemplate.query(
            "SELECT id, name, on_budget, balance FROM account ORDER BY name ASC",
            emptyMap<String, Any>(),
        ) { rs, _ ->
            Account.restore(
                AccountId(rs.getObject("id", UUID::class.java)),
                Name(rs.getString("name")),
                rs.getBoolean("on_budget"),
                Money(rs.getBigDecimal("balance")),
            )
        }
}
