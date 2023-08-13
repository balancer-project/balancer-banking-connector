package io.juancrrn.balancerbankingconnector.repository

import io.juancrrn.balancerbankingconnector.repositories.LinkTokenRepository
import io.juancrrn.balancerbankingconnector.valueobjects.LinkToken
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import io.juancrrn.plaidwebclient.api.PlaidApi
import io.juancrrn.plaidwebclient.model.CountryCode
import io.juancrrn.plaidwebclient.model.LinkTokenCreateRequest
import io.juancrrn.plaidwebclient.model.LinkTokenCreateRequestUser
import io.juancrrn.plaidwebclient.model.Products
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
            .awaitSingle()["link_token"] as String // TODO: fix property generation and mapping
        // TODO: handle errors

        return LinkToken(linkToken)
    }
}
