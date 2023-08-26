package io.juancrrn.balancerbankingconnector.repository

import io.juancrrn.balancerbankingconnector.repositories.UserRepository
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {

    override suspend fun assertExists(userId: UserId) {
        // TODO
    }
}
