package io.juancrrn.balancerbankingconnector.application.usecases

import io.juancrrn.balancerbankingconnector.application.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.application.commands.PreprocessAndNotifyTransactionAddedCommand
import io.juancrrn.balancerbankingconnector.domain.events.TransactionAddedEvent
import io.juancrrn.balancerbankingconnector.domain.events.publishers.DomainEventPublisher
import io.juancrrn.balancerbankingconnector.domain.exceptions.TransactionIsIncomingException
import io.juancrrn.balancerbankingconnector.domain.services.TransactionPreprocessor
import org.springframework.stereotype.Component

@Component
class PreprocessAndNotifyTransactionAddedUseCase(
    private val transactionPreprocessor: TransactionPreprocessor,
    private val domainEventPublisher: DomainEventPublisher,
) : CommandUseCase<PreprocessAndNotifyTransactionAddedCommand, Unit> {

    override suspend fun dispatch(command: PreprocessAndNotifyTransactionAddedCommand) = command.run {
        try {
            val preprocessedTransaction = transactionPreprocessor.preprocess(
                userId = userId,
                institutionId = institutionId,
                plaidTransaction = plaidTransaction,
            )

            domainEventPublisher.publish(TransactionAddedEvent(preprocessedTransaction))
        } catch (_: TransactionIsIncomingException) {
            // If the transaction ins incoming, ignore the notification
        }
    }
}
