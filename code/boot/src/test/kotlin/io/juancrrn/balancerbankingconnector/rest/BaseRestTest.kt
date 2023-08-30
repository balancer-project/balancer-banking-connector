package io.juancrrn.balancerbankingconnector.rest

import io.juancrrn.balancerbankingconnector.BaseTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.UUID

@AutoConfigureWebTestClient
class BaseRestTest : BaseTest() {

    @Autowired
    protected lateinit var client: WebTestClient

    companion object {

        private val DEFAULT_USER_ID = UUID.fromString("4751bd50-cc94-4182-b855-ea6895d547fb")
    }
}
