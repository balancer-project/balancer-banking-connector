package io.juancrrn.balancerbankingconnector.application.usecases

import io.juancrrn.balancerbankingconnector.application.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.application.commands.PreprocessAndNotifyTransactionRemovedCommand
import io.juancrrn.balancerbankingconnector.domain.events.TransactionRemovedEvent
import io.juancrrn.balancerbankingconnector.domain.events.publishers.DomainEventPublisher
import org.springframework.stereotype.Component

@Component
class PreprocessAndNotifyTransactionRemovedUseCase(
    private val domainEventPublisher: DomainEventPublisher,
) : CommandUseCase<PreprocessAndNotifyTransactionRemovedCommand, Unit> {

    override suspend fun dispatch(command: PreprocessAndNotifyTransactionRemovedCommand) {
        domainEventPublisher.publish(TransactionRemovedEvent(command.transactionId))
    }
}
