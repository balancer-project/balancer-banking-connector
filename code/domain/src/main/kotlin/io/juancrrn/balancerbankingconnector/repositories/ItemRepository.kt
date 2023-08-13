package io.juancrrn.balancerbankingconnector.repositories

import io.juancrrn.balancerbankingconnector.valueobjects.AccessToken
import io.juancrrn.balancerbankingconnector.valueobjects.ItemId
import io.juancrrn.balancerbankingconnector.valueobjects.PublicToken
import org.springframework.stereotype.Repository

/**
 * Repository for Plaid Items.
 */
@Repository
interface ItemRepository {

    /**
     * Exchanges a Plaid public token for a Plaid Item id and its Plaid API access token.
     *
     * @param publicToken the Plaid public token
     * @return a pair containing the Plaid Item id and its Plaid API access token
     */
    suspend fun exchangePublicToken(publicToken: PublicToken): Pair<ItemId, AccessToken>
}
