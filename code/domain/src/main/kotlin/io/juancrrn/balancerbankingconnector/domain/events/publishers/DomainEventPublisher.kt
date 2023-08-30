package io.juancrrn.balancerbankingconnector.domain.events.publishers

import io.juancrrn.balancerbankingconnector.domain.events.DomainEvent

interface DomainEventPublisher {

    suspend fun publish(message: DomainEvent)
}
