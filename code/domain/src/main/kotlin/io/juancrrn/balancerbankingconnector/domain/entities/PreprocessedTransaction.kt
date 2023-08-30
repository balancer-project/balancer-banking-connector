package io.juancrrn.balancerbankingconnector.domain.entities

import io.juancrrn.balancerbankingconnector.domain.valueobjects.InstitutionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId

data class PreprocessedTransaction(
    val id: TransactionId,
    val userId: UserId,
    val institutionId: InstitutionId,
)
