package io.juancrrn.balancerbankingconnector.infrastructure.database.models

import java.time.Instant
import java.util.UUID

data class PlaidItem(
    val id: String,
    val userId: UUID,
    val institutionId: String,
    val accessToken: String,
    val createdAt: Instant,
    val updatedAt: Instant,
) {

    object Field {

        const val ID = "id"
        const val USER_ID = "user_id"
        const val INSTITUTION_ID = "institution_id"
    }
}
