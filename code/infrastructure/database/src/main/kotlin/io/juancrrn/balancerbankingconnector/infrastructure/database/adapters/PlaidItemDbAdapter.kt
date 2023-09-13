@file:Suppress("ktlint:standard:import-ordering")

package io.juancrrn.balancerbankingconnector.infrastructure.database.adapters

import io.juancrrn.balancerbankingconnector.infrastructure.database.models.PlaidItem
import io.juancrrn.balancerbankingconnector.infrastructure.database.models.PlaidItem.Field.INSTITUTION_ID
import io.juancrrn.balancerbankingconnector.infrastructure.database.models.PlaidItem.Field.USER_ID
import java.util.UUID
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.stereotype.Component

@Component
class PlaidItemDbAdapter(
    private val entityTemplate: R2dbcEntityTemplate,
) {

    suspend fun insert(plaidItem: PlaidItem) {
        entityTemplate
            .insert(plaidItem)
            .awaitSingle()
    }

    suspend fun update(plaidItem: PlaidItem) {
        entityTemplate
            .update(plaidItem)
            .awaitSingle()
    }

    suspend fun findById(id: String): PlaidItem? {
        return entityTemplate
            .select(PlaidItem::class.java)
            .matching(query(where(PlaidItem.Field.ID).`is`(id)))
            .one()
            .awaitSingleOrNull()
    }

    suspend fun findByUserId(userId: UUID): List<PlaidItem> {
        return entityTemplate
            .select(PlaidItem::class.java)
            .matching(query(where(USER_ID).`is`(userId)))
            .all()
            .collectList()
            .awaitSingle()
    }

    suspend fun findByUserIdAndInstitutionId(userId: UUID, institutionId: String): PlaidItem? {
        return entityTemplate
            .select(PlaidItem::class.java)
            .matching(
                query(
                    where(USER_ID).`is`(userId)
                        .and(where(INSTITUTION_ID).`is`(institutionId)),
                ),
            )
            .one()
            .awaitSingleOrNull()
    }
}
