package io.juancrrn.balancerbankingconnector.config

import io.juancrrn.plaidwebclient.ApiClient
import io.juancrrn.plaidwebclient.api.PlaidApi
import io.netty.handler.logging.LogLevel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat

@Configuration
class PlaidConfig {

    @Bean("plaidWebClientProperties")
    @ConfigurationProperties(prefix = "plaid")
    fun webClientProperties(): WebClientProperties {
        return WebClientProperties()
    }

    @Bean("plaidWebClient")
    fun webClient(
        @Qualifier("plaidWebClientProperties") properties: WebClientProperties,
    ): WebClient {
        return WebClient.builder()
            .baseUrl(properties.baseUrl)
            .build()
    }

    @Bean("plaidApiClient")
    fun apiClient(
        @Qualifier("plaidWebClient") webClient: WebClient,
        @Qualifier("plaidWebClientProperties") properties: WebClientProperties,
    ): ApiClient {
        return ApiClient(webClient).apply {
            basePath = properties.baseUrl
            addDefaultHeader(CLIENT_ID_HEADER, properties.clientId)
            addDefaultHeader(SECRET_HEADER, properties.secret)
        }
    }

    @Bean
    fun plaidApi(@Qualifier("plaidApiClient") apiClient: ApiClient): PlaidApi {
        return PlaidApi(apiClient)
    }

    data class WebClientProperties(
        var baseUrl: String = DEFAULT_BASE_URL,
        var clientId: String = "",
        var secret: String = "",
    )

    companion object {

        private const val DEFAULT_BASE_URL = "https://sandbox.plaid.com"

        private const val CLIENT_ID_HEADER = "PLAID-CLIENT-ID"
        private const val SECRET_HEADER = "PLAID-SECRET"
    }
}
