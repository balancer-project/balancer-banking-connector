package io.juancrrn.balancerbankingconnector.domain.events

import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionId
import org.springframework.context.ApplicationEvent

class PlaidTransactionRemovedEvent(
    source: Any,
    val transactionId: TransactionId,
) : ApplicationEvent(source)
