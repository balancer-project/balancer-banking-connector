package io.juancrrn.balancerbankingconnector.repositories

import io.juancrrn.balancerbankingconnector.valueobjects.LinkToken
import io.juancrrn.balancerbankingconnector.valueobjects.UserId

interface LinkTokenRepository {

    /**
     * Creates a Link Token.
     */
    suspend fun create(userId: UserId): LinkToken
}
