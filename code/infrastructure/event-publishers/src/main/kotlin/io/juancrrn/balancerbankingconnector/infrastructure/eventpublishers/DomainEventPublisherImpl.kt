package io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers

import io.juancrrn.balancerbankingconnector.domain.events.DomainEvent
import io.juancrrn.balancerbankingconnector.domain.events.TransactionAddedEvent
import io.juancrrn.balancerbankingconnector.domain.events.TransactionModifiedEvent
import io.juancrrn.balancerbankingconnector.domain.events.TransactionRemovedEvent
import io.juancrrn.balancerbankingconnector.domain.events.publishers.DomainEventPublisher
import io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers.payloads.ext.toPayload
import org.slf4j.Logger
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.stereotype.Component

@Component
class DomainEventPublisherImpl(
    private val streamBridge: StreamBridge,
    private val logger: Logger,
) : DomainEventPublisher {

    override suspend fun publish(message: DomainEvent) {
        when (message) {
            is TransactionAddedEvent -> {
                logger.info("Publishing TransactionAdded event to stream bridge")
                streamBridge.send(TRANSACTION_ADDED_BINDING, message.toPayload())
            }
            is TransactionModifiedEvent -> {
                logger.info("Publishing TransactionModified event to stream bridge")
                streamBridge.send(TRANSACTION_MODIFIED_BINDING, message.toPayload())
            }
            is TransactionRemovedEvent -> {
                logger.info("Publishing TransactionRemoved event to stream bridge")
                streamBridge.send(TRANSACTION_REMOVED_BINDING, message.toPayload())
            }
        }
    }

    companion object {

        private const val TRANSACTION_ADDED_BINDING = "transactionAdded-out-0"
        private const val TRANSACTION_MODIFIED_BINDING = "transactionModified-out-0"
        private const val TRANSACTION_REMOVED_BINDING = "transactionRemoved-out-0"
    }
}
