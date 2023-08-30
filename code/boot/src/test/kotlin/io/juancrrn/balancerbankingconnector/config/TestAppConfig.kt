package io.juancrrn.balancerbankingconnector.config

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import javax.annotation.PreDestroy

@TestConfiguration
@Profile("test")
class TestAppConfig {

    private val wireMockServer = WireMockServer(
        WireMockConfiguration.wireMockConfig()
            .port(8082)
            .withRootDirectory("src/test/resources/wiremock")
            .extensions(ResponseTemplateTransformer(true)),
    ).apply {
        try {
            start()
        } catch (_: Exception) {
        }
    }

    @Bean
    fun wireMockServer(): WireMockServer {
        return wireMockServer
    }

    @PreDestroy
    fun preDestroy() {
        wireMockServer.stop()
    }
}
