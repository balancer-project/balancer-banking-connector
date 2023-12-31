package io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers.payloads.ext

import io.juancrrn.balancerbankingconnector.domain.events.TransactionAddedEvent
import io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers.payloads.TransactionAdded
import java.time.format.DateTimeFormatter

fun TransactionAddedEvent.toPayload(): TransactionAdded {
    return TransactionAdded(
        transaction.run {
            TransactionAdded.Transaction(
                id = id.id,
                userId = userId.id.toString(),
                institutionId = institutionId.id,
                accountId = accountId,
                amount = amount,
                currency = currency?.currencyCode,
                name = name,
                merchantName = merchantName,
                pending = pending,
                pendingTransactionId = pendingTransactionId?.id,
                authorizedDate = authorizedDate?.let { DateTimeFormatter.ISO_LOCAL_DATE.format(it) },
                authorizedDateTime = authorizedDateTime?.let { DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(it) },
                date = DateTimeFormatter.ISO_LOCAL_DATE.format(date),
                dateTime = dateTime?.let { DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(it) },
                paymentChannel = paymentChannel.name,
                primaryPersonalFinanceCategory = primaryPersonalFinanceCategory?.name,
                detailedPersonalFinanceCategory = detailedPersonalFinanceCategory?.name,
                type = type?.name,
            )
        },
    )
}
