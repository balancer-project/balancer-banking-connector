package io.juancrrn.balancerbankingconnector.domain.events

import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidEnvironment
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidItemId
import org.springframework.context.ApplicationEvent

class PlaidSyncUpdatesAvailableEvent(
    source: Any,
    val itemId: PlaidItemId,
    val initialUpdateComplete: Boolean,
    val historicalUpdateComplete: Boolean,
    val environment: PlaidEnvironment,
) : ApplicationEvent(source)
