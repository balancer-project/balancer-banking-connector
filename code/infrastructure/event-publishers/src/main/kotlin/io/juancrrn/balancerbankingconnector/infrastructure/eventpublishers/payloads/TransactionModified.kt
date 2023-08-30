package io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers.payloads

import java.util.UUID

data class TransactionModified(
    val transaction: Transaction,
) {

    data class Transaction(
        val id: String,
        val userId: UUID,
        val institutionId: String,
    )
}
