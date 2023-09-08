package io.juancrrn.balancerbankingconnector.infrastructure.repository.models.ext

import com.plaid.client.model.Transaction
import io.juancrrn.balancerbankingconnector.domain.entities.PlaidTransaction
import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionId

fun Transaction.toEntity(): PlaidTransaction {
    return PlaidTransaction(
        id = TransactionId(transactionId),
        accountId = accountId,
        amount = amount.toDouble(),
        isoCurrencyCode = isoCurrencyCode,
        category = category,
        categoryId = categoryId,
        name = name,
        merchantName = merchantName,
        pending = pending,
        pendingTransactionId = pendingTransactionId?.let { TransactionId(it) },
        authorizedDate = authorizedDate,
        authorizedDateTime = authorizedDatetime?.toLocalDateTime(),
        date = date,
        dateTime = datetime?.toLocalDateTime(),
        paymentChannel = paymentChannel.toVO(),
        primaryPersonalFinanceCategory =
            personalFinanceCategory?.primary?.let { getPrimaryPersonalFinanceCategory(it) },
        detailedPersonalFinanceCategory =
            personalFinanceCategory?.detailed?.let { getDetailedPersonalFinanceCategory(it) },
        type = transactionCode?.toTransactionTypeVO(),
    )
}
