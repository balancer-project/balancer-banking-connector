package io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers.payloads.ext

import io.juancrrn.balancerbankingconnector.domain.events.TransactionModifiedEvent
import io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers.payloads.TransactionModified

fun TransactionModifiedEvent.toPayload(): TransactionModified {
    return TransactionModified(
        TransactionModified.Transaction(
            id = transaction.id.id,
            userId = transaction.userId.id,
            institutionId = transaction.institutionId.id,
        ),
    )
}
