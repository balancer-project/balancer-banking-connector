package io.juancrrn.balancerbankingconnector.domain.entities

import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidTransactionId

data class PlaidTransaction(
    val id: PlaidTransactionId,
)