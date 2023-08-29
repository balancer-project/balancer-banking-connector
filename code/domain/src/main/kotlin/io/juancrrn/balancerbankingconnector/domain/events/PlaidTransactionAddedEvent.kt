package io.juancrrn.balancerbankingconnector.domain.events

import io.juancrrn.balancerbankingconnector.domain.entities.PlaidTransaction
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import org.springframework.context.ApplicationEvent

class PlaidTransactionAddedEvent(
    source: Any,
    // TODO: financial institution (Item) information would probably be useful
    val userId: UserId,
    val transaction: PlaidTransaction,
) : ApplicationEvent(source)
