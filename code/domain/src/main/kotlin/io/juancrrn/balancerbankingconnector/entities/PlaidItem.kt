package io.juancrrn.balancerbankingconnector.entities

import io.juancrrn.balancerbankingconnector.valueobjects.PlaidAccessToken
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidInstitutionId
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidItemId
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import java.time.Instant

data class PlaidItem(
    val id: PlaidItemId,
    val userId: UserId,
    val institutionId: PlaidInstitutionId,
    val accessToken: PlaidAccessToken,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now(),
)
