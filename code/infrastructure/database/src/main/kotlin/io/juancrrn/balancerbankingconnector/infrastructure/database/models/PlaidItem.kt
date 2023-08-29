package io.juancrrn.balancerbankingconnector.infrastructure.database.models

import org.springframework.data.annotation.Id
import java.time.Instant
import java.util.UUID

data class PlaidItem(
    @Id
    val id: String,
    val userId: UUID,
    val institutionId: String,
    val accessToken: String,
    val nextCursor: String?,
    val initialUpdateDone: Boolean,
    val historicalUpdateDone: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant,
) {

    object Field {

        const val ID = "id"
        const val USER_ID = "user_id"
        const val INSTITUTION_ID = "institution_id"
    }
}
