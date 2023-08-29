package io.juancrrn.balancerbankingconnector.application.usecases

import io.juancrrn.balancerbankingconnector.application.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.application.commands.SyncAndPreprocessTransactionsCommand
import io.juancrrn.balancerbankingconnector.domain.events.PlaidTransactionAddedEvent
import io.juancrrn.balancerbankingconnector.domain.events.PlaidTransactionModifiedEvent
import io.juancrrn.balancerbankingconnector.domain.events.PlaidTransactionRemovedEvent
import io.juancrrn.balancerbankingconnector.domain.events.publishers.ApplicationEventPublisher
import io.juancrrn.balancerbankingconnector.domain.repositories.PlaidItemRepository
import io.juancrrn.balancerbankingconnector.domain.repositories.PlaidTransactionRepository
import org.springframework.stereotype.Component

@Component
class SyncAndPreprocessTransactionsUseCase(
    private val plaidItemRepository: PlaidItemRepository,
    private val plaidTransactionRepository: PlaidTransactionRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,

) : CommandUseCase<SyncAndPreprocessTransactionsCommand, Unit> {

    override suspend fun dispatch(command: SyncAndPreprocessTransactionsCommand) {
        // TODO: assert command is valid

        // TODO: what to do if item does not exist?
        val item = plaidItemRepository.find(command.itemId) ?: return

        if (!item.initialUpdateDone || !item.historicalUpdateDone) {
            plaidItemRepository.save(
                item.copy(
                    nextCursor = plaidTransactionRepository.advanceCursor(item.accessToken),
                    initialUpdateDone = command.initialUpdateComplete,
                    historicalUpdateDone = command.historicalUpdateComplete,
                ),
            )
        } else {
            val syncResult = plaidTransactionRepository.sync(item.accessToken, item.nextCursor!!)

            plaidItemRepository.save(
                item.copy(nextCursor = syncResult.nextCursor),
            )

            with(syncResult) {
                added.forEach { transaction ->
                    applicationEventPublisher.publish(
                        PlaidTransactionAddedEvent(command, item.userId, transaction),
                    )
                }
                modified.forEach { transaction ->
                    applicationEventPublisher.publish(
                        PlaidTransactionModifiedEvent(command, item.userId, transaction),
                    )
                }
                removed.forEach { transactionId ->
                    applicationEventPublisher.publish(
                        PlaidTransactionRemovedEvent(command, item.userId, transactionId),
                    )
                }
            }
        }
    }
}
