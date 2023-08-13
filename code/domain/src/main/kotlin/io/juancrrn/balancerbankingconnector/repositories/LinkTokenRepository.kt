package io.juancrrn.balancerbankingconnector.repositories

import io.juancrrn.balancerbankingconnector.valueobjects.LinkToken
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import org.springframework.stereotype.Repository

/**
 * Repository for Plaid Link tokens.
 */
@Repository
interface LinkTokenRepository {

    /**
     * Creates a Plaid Link token.
     *
     * @param userId the user id
     * @return the Plaid Link token
     */
    suspend fun create(userId: UserId): LinkToken
}
