package io.juancrrn.balancerbankingconnector.infrastructure.repository.models.ext

import com.plaid.client.model.Transaction
import io.juancrrn.balancerbankingconnector.domain.entities.PlaidTransaction
import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionId

fun Transaction.toEntity(): PlaidTransaction {
    return PlaidTransaction(
        id = TransactionId(transactionId),
    )
}
