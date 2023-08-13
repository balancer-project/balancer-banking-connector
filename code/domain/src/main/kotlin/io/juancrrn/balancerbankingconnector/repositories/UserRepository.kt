package io.juancrrn.balancerbankingconnector.repositories

import io.juancrrn.balancerbankingconnector.valueobjects.AccessToken
import io.juancrrn.balancerbankingconnector.valueobjects.ItemId
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import org.springframework.stereotype.Repository

/**
 * Repository for Balancer users.
 */
@Repository
interface UserRepository {

    /**
     * Asserts that a user exists.
     *
     * @param userId User id
     */
    suspend fun assertExists(userId: UserId)

    /**
     * Associates a Plaid Item and its Plaid API access token to a user.
     *
     * @param userId the user id
     * @param itemId the Plaid Item id
     * @param accessToken the Plaid API access token
     */
    suspend fun associateItem(userId: UserId, itemId: ItemId, accessToken: AccessToken)
}
