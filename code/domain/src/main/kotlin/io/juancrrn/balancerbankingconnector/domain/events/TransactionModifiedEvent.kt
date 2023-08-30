package io.juancrrn.balancerbankingconnector.domain.events

import io.juancrrn.balancerbankingconnector.domain.entities.PreprocessedTransaction

data class TransactionModifiedEvent(
    val transaction: PreprocessedTransaction,
) : DomainEvent()
