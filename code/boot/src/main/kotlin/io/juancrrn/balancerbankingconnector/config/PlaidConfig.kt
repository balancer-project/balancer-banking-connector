package io.juancrrn.balancerbankingconnector.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.plaid.client.ApiClient
import com.plaid.client.request.PlaidApi
import io.netty.handler.logging.LogLevel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat

@Configuration
class PlaidConfig(
    private val objectMapper: ObjectMapper,
) {

    @Bean("plaidWebClientProperties")
    @ConfigurationProperties(prefix = "plaid")
    fun webClientProperties(): WebClientProperties {
        return WebClientProperties()
    }

    @Bean("plaidWebClient")
    fun webClient(
        @Qualifier("plaidWebClientProperties") properties: WebClientProperties,
    ): WebClient {
        val exchangeStrategies = ExchangeStrategies.builder()
            .codecs { configurer ->
                configurer.defaultCodecs().apply {
                    jackson2JsonEncoder(Jackson2JsonEncoder(objectMapper, APPLICATION_JSON))
                    jackson2JsonDecoder(Jackson2JsonDecoder(objectMapper, APPLICATION_JSON))
                }
            }.build()

        val clientConnector = ReactorClientHttpConnector(
            HttpClient
                .create()
                .wiretap(
                    HttpClient::class.java.canonicalName,
                    LogLevel.DEBUG,
                    AdvancedByteBufFormat.TEXTUAL,
                ),
        )

        return WebClient.builder()
            .baseUrl(properties.baseUrl)
            .exchangeStrategies(exchangeStrategies)
            .clientConnector(clientConnector)
            .build()
    }

    @Bean("plaidWebClientForTransactionsSync")
    fun webClientForTransactionsSync(
        @Qualifier("plaidWebClientProperties") properties: WebClientProperties,
        @Qualifier("plaidWebClient") webClient: WebClient,
    ): WebClient {
        return webClient.mutate()
            .baseUrl(properties.transactionsUrl)
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

    @Bean("plaidApiClientForTransactionsSync")
    fun apiClientForTransactionsSync(
        @Qualifier("plaidWebClientForTransactionsSync") webClient: WebClient,
        @Qualifier("plaidWebClientProperties") properties: WebClientProperties,
    ): ApiClient {
        return ApiClient(webClient).apply {
            basePath = properties.transactionsUrl
            addDefaultHeader(CLIENT_ID_HEADER, properties.clientId)
            addDefaultHeader(SECRET_HEADER, properties.secret)
        }
    }

    @Bean("plaidApi")
    fun plaidApi(
        @Qualifier("plaidApiClient") apiClient: ApiClient,
    ): PlaidApi {
        return PlaidApi(apiClient)
    }

    @Bean("plaidApiForTransactionsSync")
    fun plaidApiForTransactionsSync(
        @Qualifier("plaidApiClientForTransactionsSync") apiClient: ApiClient,
    ): PlaidApi {
        return PlaidApi(apiClient)
    }

    data class WebClientProperties(
        var baseUrl: String = DEFAULT_BASE_URL,
        var transactionsUrl: String = DEFAULT_BASE_URL,
        var clientId: String = "",
        var secret: String = "",
    )

    companion object {

        private const val DEFAULT_BASE_URL = "https://sandbox.plaid.com"

        private const val CLIENT_ID_HEADER = "PLAID-CLIENT-ID"
        private const val SECRET_HEADER = "PLAID-SECRET"
    }
}
