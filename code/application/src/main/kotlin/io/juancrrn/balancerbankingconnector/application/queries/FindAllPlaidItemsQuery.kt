package io.juancrrn.balancerbankingconnector.application.queries

import io.juancrrn.balancerbankingconnector.domain.entities.PlaidItem
import java.util.UUID

data class FindAllPlaidItemsQuery(
    val userId: UUID?,
) : Query<List<PlaidItem>>
