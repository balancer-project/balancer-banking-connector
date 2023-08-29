package io.juancrrn.balancerbankingconnector.domain.repositories

import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidAccessToken
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidCursor
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidTransactionsSyncResult
import org.springframework.stereotype.Repository

/**
 * Repository for Plaid transactions.
 */
@Repository
interface PlaidTransactionRepository {

    /**
     * Synchronizes the transactions for a Plaid Item, probably after receiving a SYNC_UPDATES_AVAILABLE webhook call.
     *
     * @param accessToken the access token for the Plaid Item
     * @param cursor the cursor to start from
     * @return the next cursor and the lists of transactions added, modified and removed
     */
    suspend fun sync(accessToken: PlaidAccessToken, cursor: PlaidCursor): PlaidTransactionsSyncResult

    /**
     * Advances the cursor to the last one available for a Plaid Item.
     *
     * @param accessToken the access token for the Plaid Item
     * @return the first next cursor
     */
    suspend fun advanceCursor(accessToken: PlaidAccessToken): PlaidCursor
}
