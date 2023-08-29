package io.juancrrn.balancerbankingconnector.eventlisteners

import io.juancrrn.balancerbankingconnector.application.usecases.UseCaseDispatcher
import io.juancrrn.balancerbankingconnector.domain.events.PlaidSyncUpdatesAvailableEvent
import io.juancrrn.balancerbankingconnector.domain.events.PlaidTransactionAddedEvent
import io.juancrrn.balancerbankingconnector.domain.events.PlaidTransactionModifiedEvent
import io.juancrrn.balancerbankingconnector.domain.events.PlaidTransactionRemovedEvent
import io.juancrrn.balancerbankingconnector.eventlisteners.events.ext.toFetchAndPreprocessTransactionsCommand
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationEventListener(
    private val dispatcher: UseCaseDispatcher,
    private val logger: Logger,
) {

    @EventListener
    fun handle(event: PlaidSyncUpdatesAvailableEvent) = mono {
        logger.info("Received PlaidSyncUpdatesAvailableEvent")
        dispatcher.dispatch(event.toFetchAndPreprocessTransactionsCommand())
    }

    @EventListener
    fun handle(event: PlaidTransactionAddedEvent) = mono {
        logger.info("Received PlaidTransactionAddedEvent")
    }

    @EventListener
    fun handle(event: PlaidTransactionModifiedEvent) = mono {
        logger.info("Received PlaidTransactionModifiedEvent")
    }

    @EventListener
    fun handle(event: PlaidTransactionRemovedEvent) = mono {
        logger.info("Received PlaidTransactionRemovedEvent")
    }
}
