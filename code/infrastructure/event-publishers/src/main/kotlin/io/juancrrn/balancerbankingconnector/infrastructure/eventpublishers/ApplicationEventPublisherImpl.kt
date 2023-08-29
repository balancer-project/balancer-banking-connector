package io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers

import io.juancrrn.balancerbankingconnector.domain.events.publishers.ApplicationEventPublisher
import org.springframework.context.ApplicationEvent
import org.springframework.stereotype.Component
import org.springframework.context.ApplicationEventPublisher as SpringApplicationEventPublisher

@Component
class ApplicationEventPublisherImpl(
    private val publisher: SpringApplicationEventPublisher,
) : ApplicationEventPublisher {

    override suspend fun publish(event: ApplicationEvent) {
        publisher.publishEvent(event)
    }
}
