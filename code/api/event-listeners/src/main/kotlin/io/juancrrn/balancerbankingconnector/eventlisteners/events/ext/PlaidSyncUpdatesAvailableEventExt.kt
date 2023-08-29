package io.juancrrn.balancerbankingconnector.eventlisteners.events.ext

import io.juancrrn.balancerbankingconnector.application.commands.SyncAndPreprocessTransactionsCommand
import io.juancrrn.balancerbankingconnector.domain.events.PlaidSyncUpdatesAvailableEvent

fun PlaidSyncUpdatesAvailableEvent.toFetchAndPreprocessTransactionsCommand(): SyncAndPreprocessTransactionsCommand {
    return SyncAndPreprocessTransactionsCommand(
        itemId = this.itemId,
        initialUpdateComplete = initialUpdateComplete,
        historicalUpdateComplete = historicalUpdateComplete,
        environment = environment,
    )
}
