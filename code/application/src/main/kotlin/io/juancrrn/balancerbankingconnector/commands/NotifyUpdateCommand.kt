package io.juancrrn.balancerbankingconnector.commands

data class NotifyUpdateCommand(
    val webhookType: String,
    val webhookCode: String,
    val itemId: String,
    val environment: String,
    val initialUpdateComplete: Boolean?,
    val historicalUpdateComplete: Boolean?,
    val accountIds: List<String>?,
    val newTransactions: Int?,
    val removedTransactions: List<String>?,
) : Command<Unit>
