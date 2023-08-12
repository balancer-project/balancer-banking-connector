package io.juancrrn.balancerbankingconnector.config

import com.plaid.client.ApiClient
import com.plaid.client.request.PlaidApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PlaidConfig {

    @Bean("plaidClient")
    fun plaidClient(
        @Value("\${plaid.env}")
        env: String,
        @Value("\${plaid.client-id}")
        clientId: String,
        @Value("\${plaid.secret}")
        secret: String,
    ): PlaidApi {
        val apiCredentials = hashMapOf(
            "clientId" to clientId,
            "secret" to secret,
        )

        val apiClient = ApiClient(apiCredentials).apply {
            setPlaidAdapter(decodeEnv(env))
        }

        return apiClient.createService(PlaidApi::class.java)
    }

    private fun decodeEnv(environment: String): String {
        return when (environment) {
            "sandbox" -> ApiClient.Sandbox
            "development" -> ApiClient.Development
            "production" -> ApiClient.Production
            else -> ApiClient.Sandbox
        }
    }
}
