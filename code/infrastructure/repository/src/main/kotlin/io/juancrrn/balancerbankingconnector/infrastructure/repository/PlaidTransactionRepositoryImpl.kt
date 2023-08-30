package io.juancrrn.balancerbankingconnector.infrastructure.repository

import com.plaid.client.model.Transaction
import com.plaid.client.model.TransactionsSyncRequest
import com.plaid.client.model.TransactionsSyncRequestOptions
import com.plaid.client.model.TransactionsSyncResponse
import com.plaid.client.request.PlaidApi
import io.juancrrn.balancerbankingconnector.domain.repositories.PlaidTransactionRepository
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidAccessToken
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidCursor
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidTransactionsSyncResult
import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionId
import io.juancrrn.balancerbankingconnector.infrastructure.repository.models.ext.toEntity
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Repository

@Repository
class PlaidTransactionRepositoryImpl(
    private val plaidApi: PlaidApi,
) : PlaidTransactionRepository {

    override suspend fun sync(
        accessToken: PlaidAccessToken,
        cursor: PlaidCursor,
    ): PlaidTransactionsSyncResult {
        var nextCursor = cursor.cursor
        val request = TransactionsSyncRequest()
            .accessToken(accessToken.token)
            .options(TransactionsSyncRequestOptions().includePersonalFinanceCategory(true))
            .count(SYNC_MAX_COUNT)
        var hasMore: Boolean
        val added = mutableListOf<Transaction>()
        val modified = mutableListOf<Transaction>()
        val removed = mutableListOf<String>()

        do {
            val response = plaidApi.transactionsSync(request.cursor(nextCursor)).awaitSingle()

            added.addAll(response.added)
            modified.addAll(response.modified)
            removed.addAll(response.removed.map { it.transactionId!! })

            hasMore = response.hasMore
            nextCursor = response.nextCursor
        } while (hasMore)

        return PlaidTransactionsSyncResult(
            PlaidCursor(nextCursor),
            added.map(Transaction::toEntity),
            modified.map(Transaction::toEntity),
            removed.map(::TransactionId),
        )
    }

    override suspend fun advanceCursor(accessToken: PlaidAccessToken): PlaidCursor {
        var hasMore = true
        var cursor: String? = null
        val request = TransactionsSyncRequest()
            .accessToken(accessToken.token)
            .options(TransactionsSyncRequestOptions().includePersonalFinanceCategory(true))
            .count(SYNC_MAX_COUNT)
        var response: TransactionsSyncResponse

        while (hasMore) {
            response = plaidApi.transactionsSync(request.cursor(cursor)).awaitSingle()

            hasMore = response.hasMore
            cursor = response.nextCursor
        }

        return PlaidCursor(cursor!!)
    }

    companion object {

        private const val SYNC_MAX_COUNT = 500
    }
}
