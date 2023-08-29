package io.juancrrn.balancerbankingconnector.domain.events.publishers

import org.springframework.context.ApplicationEvent

interface ApplicationEventPublisher {

    suspend fun publish(event: ApplicationEvent)
}
