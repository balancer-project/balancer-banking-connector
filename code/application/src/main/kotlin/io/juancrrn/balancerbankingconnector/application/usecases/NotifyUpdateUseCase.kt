package io.juancrrn.balancerbankingconnector.application.usecases

import io.juancrrn.balancerbankingconnector.application.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.application.commands.NotifyUpdateCommand
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidWebhookCode
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidWebhookCode.DEFAULT_UPDATE
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidWebhookCode.HISTORICAL_UPDATE
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidWebhookCode.INITIAL_UPDATE
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidWebhookCode.RECURRENT_TRANSACTIONS_UPDATE
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidWebhookCode.SYNC_UPDATES_AVAILABLE
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidWebhookCode.TRANSACTIONS_REMOVED
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidWebhookType
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidWebhookType.TRANSACTIONS
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
class NotifyUpdateUseCase(
    private val logger: Logger,
) : CommandUseCase<NotifyUpdateCommand, Unit> {

    override suspend fun dispatch(command: NotifyUpdateCommand) {
        try {
            when (PlaidWebhookType.valueOf(command.webhookType)) {
                TRANSACTIONS -> handleTransactionsWebhook(command)
            }
        } catch (_: IllegalArgumentException) {
            // Do nothing if we can't or don't want to handle the webhook type
            logger.warn("Webhook of type ${command.webhookType} not handled")
        }
    }

    private suspend fun handleTransactionsWebhook(command: NotifyUpdateCommand) = command.run {
        try {
            when (PlaidWebhookCode.valueOf(webhookCode)) {
                SYNC_UPDATES_AVAILABLE -> {
                    // TODO: publish SyncUpdatesAvailableNotified event
                }
                RECURRENT_TRANSACTIONS_UPDATE -> {
                    // TODO: publish RecurrentTransactionsUpdateNotified event
                }
                INITIAL_UPDATE -> {
                    // TODO: publish InitialUpdateNotified event
                }
                HISTORICAL_UPDATE -> {
                    // TODO: publish HistoricalUpdateNotified event
                }
                DEFAULT_UPDATE -> {
                    // TODO: publish DefaultUpdateNotified event
                }
                TRANSACTIONS_REMOVED -> {
                    // TODO: publish TransactionsRemovedNotified event
                }
            }
        } catch (_: IllegalArgumentException) {
            // Do nothing if we can't or don't want to handle the webhook code
            logger.warn("Webhook with code ${command.webhookCode} not handled")
        }
    }
}
