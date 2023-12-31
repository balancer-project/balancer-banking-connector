package io.juancrrn.balancerbankingconnector.infrastructure.repository.models.ext

import io.juancrrn.balancerbankingconnector.domain.valueobjects.InstitutionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidAccessToken
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidCursor
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidItemId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import io.juancrrn.balancerbankingconnector.infrastructure.database.models.PlaidItem
import io.juancrrn.balancerbankingconnector.domain.entities.PlaidItem as PlaidItemEntity

fun PlaidItem.toEntity(): PlaidItemEntity {
    return PlaidItemEntity(
        id = PlaidItemId(id),
        userId = UserId(userId),
        institutionId = InstitutionId(institutionId),
        accessToken = PlaidAccessToken(accessToken),
        nextCursor = nextCursor?.let { PlaidCursor(it) },
        initialUpdateDone = initialUpdateDone,
        historicalUpdateDone = historicalUpdateDone,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}

fun PlaidItemEntity.toModel(): PlaidItem {
    return PlaidItem(
        id = id.id,
        userId = userId.id,
        institutionId = institutionId.id,
        accessToken = accessToken.token,
        nextCursor = nextCursor?.cursor,
        initialUpdateDone = initialUpdateDone,
        historicalUpdateDone = historicalUpdateDone,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}
