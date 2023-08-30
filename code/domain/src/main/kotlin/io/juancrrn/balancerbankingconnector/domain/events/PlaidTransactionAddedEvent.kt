package io.juancrrn.balancerbankingconnector.domain.events

import io.juancrrn.balancerbankingconnector.domain.entities.PlaidTransaction
import io.juancrrn.balancerbankingconnector.domain.valueobjects.InstitutionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import org.springframework.context.ApplicationEvent

class PlaidTransactionAddedEvent(
    source: Any,
    val userId: UserId,
    val institutionId: InstitutionId,
    val transaction: PlaidTransaction,
) : ApplicationEvent(source)
