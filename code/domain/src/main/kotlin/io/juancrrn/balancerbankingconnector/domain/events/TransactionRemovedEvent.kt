package io.juancrrn.balancerbankingconnector.domain.events

import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionId

data class TransactionRemovedEvent(
    val transactionId: TransactionId,
) : DomainEvent()
