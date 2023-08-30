package io.juancrrn.balancerbankingconnector.domain.events

import io.juancrrn.balancerbankingconnector.domain.entities.PreprocessedTransaction

data class TransactionAddedEvent(
    val transaction: PreprocessedTransaction,
) : DomainEvent()
