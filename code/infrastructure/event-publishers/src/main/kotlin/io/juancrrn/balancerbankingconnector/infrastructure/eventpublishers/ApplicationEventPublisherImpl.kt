package io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers

import io.juancrrn.balancerbankingconnector.domain.events.publishers.ApplicationEventPublisher
import org.slf4j.Logger
import org.springframework.context.ApplicationEvent
import org.springframework.stereotype.Component
import org.springframework.context.ApplicationEventPublisher as SpringApplicationEventPublisher

@Component
class ApplicationEventPublisherImpl(
    private val publisher: SpringApplicationEventPublisher,
    private val logger: Logger,
) : ApplicationEventPublisher {

    override suspend fun publish(event: ApplicationEvent) {
        logger.info("Publishing ${event.javaClass.simpleName} application event")
        publisher.publishEvent(event)
    }
}
