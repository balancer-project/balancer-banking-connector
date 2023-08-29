package io.juancrrn.balancerbankingconnector.application.commands

import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidEnvironment
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidItemId

data class SyncAndPreprocessTransactionsCommand(
    val itemId: PlaidItemId,
    val initialUpdateComplete: Boolean,
    val historicalUpdateComplete: Boolean,
    val environment: PlaidEnvironment,
) : Command<Unit>
