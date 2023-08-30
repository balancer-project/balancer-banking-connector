package io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers.payloads.ext

import io.juancrrn.balancerbankingconnector.domain.events.TransactionRemovedEvent
import io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers.payloads.TransactionRemoved

fun TransactionRemovedEvent.toPayload(): TransactionRemoved {
    return TransactionRemoved(
        transactionId = transactionId.id,
    )
}
