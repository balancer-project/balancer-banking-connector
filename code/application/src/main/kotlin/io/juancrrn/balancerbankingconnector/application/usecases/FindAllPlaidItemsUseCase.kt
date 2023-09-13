package io.juancrrn.balancerbankingconnector.application.usecases

import io.juancrrn.balancerbankingconnector.application.queries.FindAllPlaidItemsQuery
import io.juancrrn.balancerbankingconnector.application.queries.QueryUseCase
import io.juancrrn.balancerbankingconnector.domain.entities.PlaidItem
import io.juancrrn.balancerbankingconnector.domain.repositories.PlaidItemRepository
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import org.springframework.stereotype.Component

@Component
class FindAllPlaidItemsUseCase(
    private val plaidItemRepository: PlaidItemRepository,
) : QueryUseCase<FindAllPlaidItemsQuery, List<PlaidItem>> {

    override suspend fun dispatch(query: FindAllPlaidItemsQuery): List<PlaidItem> {
        // TODO: validate query

        // TODO: validate that the user exists

        return plaidItemRepository.find(UserId(query.userId!!))
    }
}
