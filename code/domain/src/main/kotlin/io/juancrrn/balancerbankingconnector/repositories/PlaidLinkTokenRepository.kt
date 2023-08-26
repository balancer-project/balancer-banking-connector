package io.juancrrn.balancerbankingconnector.repositories

import io.juancrrn.balancerbankingconnector.valueobjects.PlaidLinkToken
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import org.springframework.stereotype.Repository

/**
 * Repository for Plaid Link tokens.
 */
@Repository
interface PlaidLinkTokenRepository {

    /**
     * Creates a Plaid Link token.
     *
     * @param userId the user id
     * @return the Plaid Link token
     */
    suspend fun create(userId: UserId): PlaidLinkToken
}
