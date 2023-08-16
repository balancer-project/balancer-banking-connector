package io.juancrrn.balancerbankingconnector.restserver.api.requests.ext

import io.juancrrn.balancerbankingconnector.commands.NotifyUpdateCommand
import io.juancrrn.balancerbankingconnector.restserver.api.models.NotifyUpdateRequestBody

fun NotifyUpdateRequestBody.toCommand(): NotifyUpdateCommand {
    return NotifyUpdateCommand(
        webhookCode = webhookCode,
        webhookType = webhookType,
        itemId = itemId,
        environment = environment,
        initialUpdateComplete = initialUpdateComplete,
        historicalUpdateComplete = historicalUpdateComplete,
        accountIds = accountIds,
        newTransactions = newTransactions,
        removedTransactions = removedTransactions,
    )
}
