package io.juancrrn.balancerbankingconnector.repository

import com.plaid.client.model.ItemPublicTokenExchangeRequest
import com.plaid.client.request.PlaidApi
import io.juancrrn.balancerbankingconnector.repositories.ItemRepository
import io.juancrrn.balancerbankingconnector.valueobjects.AccessToken
import io.juancrrn.balancerbankingconnector.valueobjects.ItemId
import io.juancrrn.balancerbankingconnector.valueobjects.PublicToken
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryImpl(
    private val plaidApi: PlaidApi,
) : ItemRepository {

    override suspend fun exchangePublicToken(publicToken: PublicToken): Pair<ItemId, AccessToken> {
        val request = ItemPublicTokenExchangeRequest()
            .publicToken(publicToken.token)

        val response = plaidApi
            .itemPublicTokenExchange(request)
            .awaitSingle()

        return Pair(
            ItemId.fromString(response.itemId),
            AccessToken(response.accessToken),
        )
    }
}
