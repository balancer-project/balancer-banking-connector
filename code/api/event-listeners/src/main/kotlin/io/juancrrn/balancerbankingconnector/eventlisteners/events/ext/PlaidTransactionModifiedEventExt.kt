package io.juancrrn.balancerbankingconnector.eventlisteners.events.ext

import io.juancrrn.balancerbankingconnector.application.commands.PreprocessAndNotifyTransactionModifiedCommand
import io.juancrrn.balancerbankingconnector.domain.events.PlaidTransactionModifiedEvent

fun PlaidTransactionModifiedEvent.toPreprocessAndNotifyTransactionModifiedCommand():
    PreprocessAndNotifyTransactionModifiedCommand {
    return PreprocessAndNotifyTransactionModifiedCommand(
        userId = userId,
        institutionId = institutionId,
        plaidTransaction = transaction,
    )
}
