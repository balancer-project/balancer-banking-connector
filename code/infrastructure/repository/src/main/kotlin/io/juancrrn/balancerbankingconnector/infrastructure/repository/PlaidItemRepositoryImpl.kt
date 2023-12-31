package io.juancrrn.balancerbankingconnector.infrastructure.repository

import com.plaid.client.model.ItemPublicTokenExchangeRequest
import com.plaid.client.model.PlaidError
import com.plaid.client.request.PlaidApi
import io.juancrrn.balancerbankingconnector.domain.entities.PlaidItem
import io.juancrrn.balancerbankingconnector.domain.exceptions.InvalidPlaidPublicTokenException
import io.juancrrn.balancerbankingconnector.domain.repositories.PlaidItemRepository
import io.juancrrn.balancerbankingconnector.domain.valueobjects.InstitutionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidAccessToken
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidItemId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidPublicToken
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import io.juancrrn.balancerbankingconnector.infrastructure.database.adapters.PlaidItemDbAdapter
import io.juancrrn.balancerbankingconnector.infrastructure.repository.models.ext.toEntity
import io.juancrrn.balancerbankingconnector.infrastructure.repository.models.ext.toModel
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClientResponseException

@Repository
class PlaidItemRepositoryImpl(
    @Qualifier("plaidApi")
    private val plaidApi: PlaidApi,
    private val plaidItemDbAdapter: PlaidItemDbAdapter,
) : PlaidItemRepository {

    override suspend fun save(item: PlaidItem) {
        if (find(item.id) == null) {
            plaidItemDbAdapter.insert(item.toModel())
        } else {
            plaidItemDbAdapter.update(item.toModel())
        }
    }

    override suspend fun find(id: PlaidItemId): PlaidItem? {
        return plaidItemDbAdapter.findById(id.id)?.toEntity()
    }

    override suspend fun find(userId: UserId): List<PlaidItem> {
        return plaidItemDbAdapter.findByUserId(userId.id).map { it.toEntity() }
    }

    override suspend fun find(userId: UserId, institutionId: InstitutionId): PlaidItem? {
        return plaidItemDbAdapter.findByUserIdAndInstitutionId(userId.id, institutionId.id)?.toEntity()
    }

    override suspend fun exchangePublicToken(publicToken: PlaidPublicToken): Pair<PlaidItemId, PlaidAccessToken> {
        val request = ItemPublicTokenExchangeRequest()
            .publicToken(publicToken.token)

        val response = try {
            plaidApi
                .itemPublicTokenExchange(request)
                .awaitSingle()
        } catch (e: WebClientResponseException.BadRequest) {
            if (e.getResponseBodyAs(PlaidError::class.java)?.errorCode == "INVALID_PUBLIC_TOKEN") {
                throw InvalidPlaidPublicTokenException(publicToken)
            } else {
                throw e
            }
        }

        return Pair(
            PlaidItemId(response.itemId),
            PlaidAccessToken(response.accessToken),
        )
    }
}
