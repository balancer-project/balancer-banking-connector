package io.juancrrn.balancerbankingconnector.repository.models.ext

import io.juancrrn.balancerbankingconnector.infrastructure.database.models.PlaidItem
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidAccessToken
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidInstitutionId
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidItemId
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import io.juancrrn.balancerbankingconnector.entities.PlaidItem as PlaidItemEntity

fun PlaidItem.toEntity(): PlaidItemEntity {
    return PlaidItemEntity(
        id = PlaidItemId(id),
        userId = UserId(userId),
        institutionId = PlaidInstitutionId(institutionId),
        accessToken = PlaidAccessToken(accessToken),
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
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}
