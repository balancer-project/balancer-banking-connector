package io.juancrrn.balancerbankingconnector.eventlisteners.events.ext

import io.juancrrn.balancerbankingconnector.application.commands.PreprocessAndNotifyTransactionAddedCommand
import io.juancrrn.balancerbankingconnector.domain.events.PlaidTransactionAddedEvent

fun PlaidTransactionAddedEvent.toPreprocessAndNotifyTransactionAddedCommand():
    PreprocessAndNotifyTransactionAddedCommand {
    return PreprocessAndNotifyTransactionAddedCommand(
        userId = userId,
        institutionId = institutionId,
        plaidTransaction = transaction,
    )
}
