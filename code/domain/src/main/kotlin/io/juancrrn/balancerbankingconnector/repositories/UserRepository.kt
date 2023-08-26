package io.juancrrn.balancerbankingconnector.repositories

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
}
