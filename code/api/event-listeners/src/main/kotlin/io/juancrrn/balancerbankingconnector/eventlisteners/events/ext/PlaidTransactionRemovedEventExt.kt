package io.juancrrn.balancerbankingconnector.eventlisteners.events.ext

import io.juancrrn.balancerbankingconnector.application.commands.PreprocessAndNotifyTransactionRemovedCommand
import io.juancrrn.balancerbankingconnector.domain.events.PlaidTransactionRemovedEvent

fun PlaidTransactionRemovedEvent.toPreprocessAndNotifyTransactionRemovedCommand():
    PreprocessAndNotifyTransactionRemovedCommand {
    return PreprocessAndNotifyTransactionRemovedCommand(
        transactionId = transactionId,
    )
}
