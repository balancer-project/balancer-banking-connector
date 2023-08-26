package io.juancrrn.balancerbankingconnector.domain.repositories

import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
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
