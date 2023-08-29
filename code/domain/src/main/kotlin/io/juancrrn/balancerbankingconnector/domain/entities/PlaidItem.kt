package io.juancrrn.balancerbankingconnector.domain.entities

import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidAccessToken
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidCursor
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidInstitutionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidItemId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import java.time.Instant

data class PlaidItem(
    val id: PlaidItemId,
    val userId: UserId,
    val institutionId: PlaidInstitutionId,
    val accessToken: PlaidAccessToken,
    val nextCursor: PlaidCursor? = null,
    val initialUpdateDone: Boolean = false,
    val historicalUpdateDone: Boolean = false,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now(),
)
