package io.juancrrn.balancerbankingconnector.repositories

import io.juancrrn.balancerbankingconnector.domain.exceptions.UserAndInstitutionAlreadyLinkedException
import io.juancrrn.balancerbankingconnector.entities.PlaidItem
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidAccessToken
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidInstitutionId
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidItemId
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidPublicToken
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import org.springframework.stereotype.Repository

/**
 * Repository for Plaid Items.
 */
@Repository
interface PlaidItemRepository {

    /**
     * Saves a Plaid Item.
     *
     * @param item the Plaid Item to save
     */
    suspend fun save(item: PlaidItem)

    /**
     * Finds a Plaid Item by its id and institution id.
     *
     * @param userId the user id
     * @param institutionId the institution id
     * @return the Plaid Item if found, null otherwise
     */
    suspend fun find(userId: UserId, institutionId: PlaidInstitutionId): PlaidItem?

    /**
     * Exchanges a Plaid public token for a Plaid Item id and its Plaid API access token.
     *
     * @param publicToken the Plaid public token
     * @return a pair containing the Plaid Item id and its Plaid API access token
     */
    suspend fun exchangePublicToken(publicToken: PlaidPublicToken): Pair<PlaidItemId, PlaidAccessToken>
}

suspend fun PlaidItemRepository.assertNotAlreadyLinked(userId: UserId, institutionId: PlaidInstitutionId) {
    if (find(userId, institutionId) != null) {
        throw UserAndInstitutionAlreadyLinkedException(userId, institutionId)
    }
}
