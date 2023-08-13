package io.juancrrn.balancerbankingconnector.repository

import io.juancrrn.balancerbankingconnector.repositories.UserRepository
import io.juancrrn.balancerbankingconnector.valueobjects.AccessToken
import io.juancrrn.balancerbankingconnector.valueobjects.ItemId
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {

    override suspend fun assertExists(userId: UserId) {
        // TODO: implement
    }

    override suspend fun associateItem(userId: UserId, itemId: ItemId, accessToken: AccessToken) {
        // TODO: actually save the access token in the DB

        println("### Saving access token in the DB...")
        println("userId: $userId")
        println("itemId: $itemId")
        println("accessToken: $accessToken")
    }
}
