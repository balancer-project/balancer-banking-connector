package io.juancrrn.balancerbankingconnector.infrastructure.repository

import io.juancrrn.balancerbankingconnector.domain.repositories.UserRepository
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {

    override suspend fun assertExists(userId: UserId) {
        // TODO
    }
}
