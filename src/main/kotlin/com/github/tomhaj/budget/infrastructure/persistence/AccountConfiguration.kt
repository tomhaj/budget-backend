package com.github.tomhaj.budget.infrastructure.persistence

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration(proxyBeanMethods = false)
class AccountConfiguration {
    @Bean
    fun accountRepository(jdbcTemplate: NamedParameterJdbcTemplate) = AccountRepositoryJdbcAdapter(jdbcTemplate)
}
