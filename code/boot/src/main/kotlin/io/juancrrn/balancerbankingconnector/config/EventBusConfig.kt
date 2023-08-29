package io.juancrrn.balancerbankingconnector.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ApplicationEventMulticaster
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class ApplicationEventBusConfig {

    @Bean(name = ["applicationEventMulticaster"])
    fun applicationEventMulticaster(): ApplicationEventMulticaster {
        return SimpleApplicationEventMulticaster().apply {
            setTaskExecutor(ThreadPoolTaskExecutor().apply { initialize() })
        }
    }
}
