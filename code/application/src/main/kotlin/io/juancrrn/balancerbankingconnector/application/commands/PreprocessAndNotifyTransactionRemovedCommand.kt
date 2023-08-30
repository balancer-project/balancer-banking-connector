package io.juancrrn.balancerbankingconnector.application.commands

import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionId

data class PreprocessAndNotifyTransactionRemovedCommand(
    val transactionId: TransactionId,
) : Command<Unit>
