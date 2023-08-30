package io.juancrrn.balancerbankingconnector.domain.entities

import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionId

data class PlaidTransaction(
    val id: TransactionId,
)
