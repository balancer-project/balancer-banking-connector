package io.juancrrn.balancerbankingconnector.repository

import com.plaid.client.model.CountryCode
import com.plaid.client.model.LinkTokenCreateRequest
import com.plaid.client.model.LinkTokenCreateRequestUser
import com.plaid.client.model.Products
import com.plaid.client.request.PlaidApi
import io.juancrrn.balancerbankingconnector.repositories.LinkTokenRepository
import io.juancrrn.balancerbankingconnector.valueobjects.LinkToken
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Repository

@Repository
class LinkTokenRepositoryImpl(
    private val plaidApi: PlaidApi,
) : LinkTokenRepository {

    override suspend fun create(userId: UserId): LinkToken {
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

        return LinkToken(linkToken)
    }
}
