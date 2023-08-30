package io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers.payloads.ext

import io.juancrrn.balancerbankingconnector.domain.events.TransactionAddedEvent
import io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers.payloads.TransactionAdded

fun TransactionAddedEvent.toPayload(): TransactionAdded {
    return TransactionAdded(
        TransactionAdded.Transaction(
            id = transaction.id.id,
            userId = transaction.userId.id,
            institutionId = transaction.institutionId.id,
        ),
    )
}
