package io.juancrrn.balancerbankingconnector.domain.events

import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidTransactionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import org.springframework.context.ApplicationEvent

class PlaidTransactionRemovedEvent(
    source: Any,
    // TODO: financial institution (Item) information would probably be useful
    val userId: UserId,
    val transactionId: PlaidTransactionId,
) : ApplicationEvent(source)
