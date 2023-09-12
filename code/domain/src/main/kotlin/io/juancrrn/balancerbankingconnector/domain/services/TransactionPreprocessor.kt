package io.juancrrn.balancerbankingconnector.domain.services

import io.juancrrn.balancerbankingconnector.domain.entities.PlaidTransaction
import io.juancrrn.balancerbankingconnector.domain.entities.PreprocessedTransaction
import io.juancrrn.balancerbankingconnector.domain.exceptions.TransactionIsIncomingException
import io.juancrrn.balancerbankingconnector.domain.valueobjects.InstitutionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import org.springframework.stereotype.Service
import java.util.Currency

@Service
class TransactionPreprocessor {

    suspend fun preprocess(
        userId: UserId,
        institutionId: InstitutionId,
        plaidTransaction: PlaidTransaction,
    ): PreprocessedTransaction = plaidTransaction.run {
        if (amount < 0) {
            throw TransactionIsIncomingException()
        }

        return PreprocessedTransaction(
            id = id,
            userId = userId,
            institutionId = institutionId,
            accountId = accountId,
            amount = amount,
            currency = isoCurrencyCode?.let { Currency.getInstance(it) },
            name = name,
            merchantName = merchantName,
            pending = pending,
            pendingTransactionId = pendingTransactionId,
            authorizedDate = authorizedDate,
            authorizedDateTime = authorizedDateTime,
            date = date,
            dateTime = dateTime,
            paymentChannel = paymentChannel,
            primaryPersonalFinanceCategory = primaryPersonalFinanceCategory,
            detailedPersonalFinanceCategory = detailedPersonalFinanceCategory,
            type = type,
        )
    }
}
