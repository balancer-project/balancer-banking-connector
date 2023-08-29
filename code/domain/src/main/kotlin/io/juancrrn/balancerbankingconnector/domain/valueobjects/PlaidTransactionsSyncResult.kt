package io.juancrrn.balancerbankingconnector.domain.valueobjects

import io.juancrrn.balancerbankingconnector.domain.entities.PlaidTransaction

data class PlaidTransactionsSyncResult(
    val nextCursor: PlaidCursor,
    val added: List<PlaidTransaction>,
    val modified: List<PlaidTransaction>,
    val removed: List<PlaidTransactionId>,
)
