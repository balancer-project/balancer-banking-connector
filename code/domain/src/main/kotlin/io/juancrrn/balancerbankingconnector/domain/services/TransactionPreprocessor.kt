package io.juancrrn.balancerbankingconnector.domain.services

import io.juancrrn.balancerbankingconnector.domain.entities.PlaidTransaction
import io.juancrrn.balancerbankingconnector.domain.entities.PreprocessedTransaction
import io.juancrrn.balancerbankingconnector.domain.valueobjects.InstitutionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import org.springframework.stereotype.Service

@Service
class TransactionPreprocessor {

    suspend fun preprocess(
        userId: UserId,
        institutionId: InstitutionId,
        plaidTransaction: PlaidTransaction,
    ): PreprocessedTransaction {
        // TODO: implement actual preprocessing

        return PreprocessedTransaction(
            id = plaidTransaction.id,
            userId = userId,
            institutionId = institutionId,
        )
    }
}
