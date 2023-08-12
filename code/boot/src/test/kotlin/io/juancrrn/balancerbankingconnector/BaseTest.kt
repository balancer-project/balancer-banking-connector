package io.juancrrn.balancerbankingconnector

import io.juancrrn.balancerbankingconnector.config.TestAppConfig
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [TestAppConfig::class])
class BaseTest {

    @BeforeEach
    fun beforeEach() {
    }

    @AfterEach
    fun afterEach() {
    }
}
