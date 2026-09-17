package com.github.tomhaj.budget

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.postgresql.PostgreSQLContainer

@SpringBootTest
abstract class IntegrationTest {
    companion object {
        @ServiceConnection
        @JvmStatic
        val postgres = PostgreSQLContainer("postgres:18-alpine").apply { start() }
    }
}
