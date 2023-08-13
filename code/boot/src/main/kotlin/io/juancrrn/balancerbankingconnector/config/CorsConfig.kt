package io.juancrrn.balancerbankingconnector.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFlux
class CorsConfig(
    @Value("\${rest-server.cors.allowed-origins:}")
    val corsAllowedOriginsProperty: Array<String>,
) : WebFluxConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .apply {
                corsAllowedOriginsProperty.forEach { allowedOrigin ->
                    allowedOrigins(allowedOrigin)
                }
            }
    }
}
