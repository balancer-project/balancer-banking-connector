package io.juancrrn.balancerbankingconnector.repository

import com.plaid.client.model.ItemPublicTokenExchangeRequest
import com.plaid.client.model.PlaidError
import com.plaid.client.request.PlaidApi
import io.juancrrn.balancerbankingconnector.domain.exceptions.InvalidPlaidPublicTokenException
import io.juancrrn.balancerbankingconnector.entities.PlaidItem
import io.juancrrn.balancerbankingconnector.infrastructure.database.adapters.PlaidItemDbAdapter
import io.juancrrn.balancerbankingconnector.repositories.PlaidItemRepository
import io.juancrrn.balancerbankingconnector.repository.models.ext.toEntity
import io.juancrrn.balancerbankingconnector.repository.models.ext.toModel
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidAccessToken
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidInstitutionId
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidItemId
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidPublicToken
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClientResponseException

@Repository
class PlaidItemRepositoryImpl(
    private val plaidApi: PlaidApi,
    private val plaidItemDbAdapter: PlaidItemDbAdapter,
) : PlaidItemRepository {

    override suspend fun save(item: PlaidItem) {
        plaidItemDbAdapter.save(item.toModel())
    }

    override suspend fun find(userId: UserId, institutionId: PlaidInstitutionId): PlaidItem? {
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
