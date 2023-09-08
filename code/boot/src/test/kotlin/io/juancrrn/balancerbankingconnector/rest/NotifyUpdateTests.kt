package io.juancrrn.balancerbankingconnector.rest

import io.juancrrn.balancerbankingconnector.infrastructure.database.models.PlaidItem
import io.juancrrn.balancerbankingconnector.restserver.api.models.NotifyUpdateRequestBody
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.binder.test.OutputDestination
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration
import org.springframework.context.annotation.Import
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.messaging.Message

@ExperimentalCoroutinesApi
@Import(TestChannelBinderConfiguration::class)
class NotifyUpdateTest(
    @Autowired
    private val entityTemplate: R2dbcEntityTemplate,
    @Autowired
    private val outputDestination: OutputDestination,
) : BaseRestTest() {

    @Test
    fun `notify updates given SYNC_UPDATES_AVAILABLE code and newly created Item then returns OK, advances Item cursor and saves Item initial update and historical update status`() = runTest {
        val request = NotifyUpdateRequestBody(
            webhookType = "TRANSACTIONS",
            webhookCode = "SYNC_UPDATES_AVAILABLE",
            itemId = ITEM_WITH_NULL_CURSOR__ID,
            initialUpdateComplete = true,
            historicalUpdateComplete = true,
            environment = "sandbox",
        )

        client
            .post()
            .uri(NOTIFY_UPDATE_ENDPOINT_PATH)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody().isEmpty

        val item = entityTemplate
            .select(PlaidItem::class.java)
            .matching(query(where("id").`is`(ITEM_WITH_NULL_CURSOR__ID)))
            .one()
            .awaitSingle()

        with(item) {
            assertEquals(ADVANCED_CURSOR_FOR_INITIALLY_NULL, nextCursor)
            assertTrue(initialUpdateDone)
            assertTrue(historicalUpdateDone)
        }
    }

    @Test
    fun `notify updates given SYNC_UPDATES_AVAILABLE code and existing Item with initial and historical updates completed then returns OK, advances Item cursor and publishes transactions update events`() = runTest {
        val request = NotifyUpdateRequestBody(
            webhookType = "TRANSACTIONS",
            webhookCode = "SYNC_UPDATES_AVAILABLE",
            itemId = ITEM_WITH_VALID_CURSOR__ID,
            initialUpdateComplete = true,
            historicalUpdateComplete = true,
            environment = "sandbox",
        )

        client
            .post()
            .uri(NOTIFY_UPDATE_ENDPOINT_PATH)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody().isEmpty

        val item = entityTemplate
            .select(PlaidItem::class.java)
            .matching(query(where("id").`is`(ITEM_WITH_VALID_CURSOR__ID)))
            .one()
            .awaitSingle()

        with(item) {
            assertEquals(ADVANCED_CURSOR_FOR_INITIALLY_VALID, nextCursor)
            assertTrue(initialUpdateDone)
            assertTrue(historicalUpdateDone)
        }

        val messages = receiveAll(TRANSACTION_EVENTS_BINDING)

        assertEquals(6, messages.size)
    }

    private fun receiveAll(bindingName: String): List<Message<ByteArray>> {
        val messages = mutableListOf<Message<ByteArray>>()
        var currentMessage: Message<ByteArray>?

        do {
            currentMessage = outputDestination.receive(100, bindingName)
            if (currentMessage != null) {
                messages.add(currentMessage)
            }
        } while (currentMessage != null)

        return messages
    }

    companion object {

        private const val NOTIFY_UPDATE_ENDPOINT_PATH = "/v1/bank-link/notify-update"

        private const val ITEM_WITH_NULL_CURSOR__ID = "heRERCRV4k0mHrgJEwERGzM1PtDvRJ6JcPXLT"
        private const val ITEM_WITH_VALID_CURSOR__ID = "dSZDzMx2M2KKLKMbNfGccCgGCeb9mv2KWJ0Y0"

        private const val ADVANCED_CURSOR_FOR_INITIALLY_NULL = "n36N6a0UP2UuZcfgR6RCTminkA9S72hfJTE9h4GEX1WtEHzMkjLyM" +
            "6C9UBjw9LjdYTg85G2DMMabbadmqUUG32qTwWkimPqX1PkhigLAF8r4VDVG"
        private const val ADVANCED_CURSOR_FOR_INITIALLY_VALID = "C5nhh8evxELdvBNLuFbthMjpkPRLT89mLP4ciC7wBhrzEhQNZC3X" +
            "KtVh7u0YjQaFM2SwvUUk9F7A0QTEG572LgpdQ7MVdp99Xmip4GWCDiUje4rp"

        private const val TRANSACTION_EVENTS_BINDING = "local.balancer_core.transaction_events"
    }
}
