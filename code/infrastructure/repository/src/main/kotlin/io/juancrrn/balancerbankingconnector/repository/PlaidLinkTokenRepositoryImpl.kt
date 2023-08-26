package io.juancrrn.balancerbankingconnector.repository

import com.plaid.client.model.CountryCode
import com.plaid.client.model.LinkTokenCreateRequest
import com.plaid.client.model.LinkTokenCreateRequestUser
import com.plaid.client.model.Products
import com.plaid.client.request.PlaidApi
import io.juancrrn.balancerbankingconnector.repositories.PlaidLinkTokenRepository
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidLinkToken
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Repository

@Repository
class PlaidLinkTokenRepositoryImpl(
    private val plaidApi: PlaidApi,
) : PlaidLinkTokenRepository {

    override suspend fun create(userId: UserId): PlaidLinkToken {
        val request = LinkTokenCreateRequest()
            .clientName("Balancer") // TODO: set globally by default
            .language("es") // TODO: set globally by default
            .countryCodes(listOf(CountryCode.ES))
            .user(LinkTokenCreateRequestUser().clientUserId(userId.toString()))
            .products(listOf(Products.AUTH, Products.TRANSACTIONS))

        val linkToken = plaidApi
            .linkTokenCreate(request)
            .awaitSingle()
            .linkToken
        // TODO: handle errors

        return PlaidLinkToken(linkToken)
    }
}
