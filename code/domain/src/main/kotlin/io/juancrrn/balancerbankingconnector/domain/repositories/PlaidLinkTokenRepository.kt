package io.juancrrn.balancerbankingconnector.domain.repositories

import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidLinkToken
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
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
