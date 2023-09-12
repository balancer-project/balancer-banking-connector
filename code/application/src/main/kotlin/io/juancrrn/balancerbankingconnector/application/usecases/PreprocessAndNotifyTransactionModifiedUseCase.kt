package io.juancrrn.balancerbankingconnector.application.usecases

import io.juancrrn.balancerbankingconnector.application.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.application.commands.PreprocessAndNotifyTransactionModifiedCommand
import io.juancrrn.balancerbankingconnector.domain.events.TransactionModifiedEvent
import io.juancrrn.balancerbankingconnector.domain.events.publishers.DomainEventPublisher
import io.juancrrn.balancerbankingconnector.domain.exceptions.TransactionIsIncomingException
import io.juancrrn.balancerbankingconnector.domain.services.TransactionPreprocessor
import org.springframework.stereotype.Component

@Component
class PreprocessAndNotifyTransactionModifiedUseCase(
    private val transactionPreprocessor: TransactionPreprocessor,
    private val domainEventPublisher: DomainEventPublisher,
) : CommandUseCase<PreprocessAndNotifyTransactionModifiedCommand, Unit> {

    override suspend fun dispatch(command: PreprocessAndNotifyTransactionModifiedCommand) = command.run {
        try {
            val preprocessedTransaction = transactionPreprocessor.preprocess(
                userId = userId,
                institutionId = institutionId,
                plaidTransaction = plaidTransaction,
            )

            domainEventPublisher.publish(TransactionModifiedEvent(preprocessedTransaction))
        } catch (_: TransactionIsIncomingException) {
            // If the transaction ins incoming, ignore the notification
        }
    }
}
