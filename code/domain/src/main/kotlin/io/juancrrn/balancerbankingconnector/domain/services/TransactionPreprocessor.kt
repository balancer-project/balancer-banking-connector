package io.juancrrn.balancerbankingconnector.domain.services

import io.juancrrn.balancerbankingconnector.domain.entities.PlaidTransaction
import io.juancrrn.balancerbankingconnector.domain.entities.PreprocessedTransaction
import org.springframework.stereotype.Service

@Service
class TransactionPreprocessor {

    suspend fun preprocess(plaidTransaction: PlaidTransaction): PreprocessedTransaction {
        // TODO: implement actual preprocessing
        return PreprocessedTransaction(plaidTransaction.id)
    }
}
