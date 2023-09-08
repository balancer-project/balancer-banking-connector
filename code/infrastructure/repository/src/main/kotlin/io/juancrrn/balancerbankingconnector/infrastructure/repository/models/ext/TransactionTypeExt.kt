package io.juancrrn.balancerbankingconnector.infrastructure.repository.models.ext

import com.plaid.client.model.TransactionCode
import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionType

fun TransactionCode.toTransactionTypeVO(): TransactionType? {
    return when (this) {
        TransactionCode.ADJUSTMENT -> TransactionType.ADJUSTMENT
        TransactionCode.ATM -> TransactionType.ATM
        TransactionCode.BANK_CHARGE -> TransactionType.BANK_CHARGE
        TransactionCode.BILL_PAYMENT -> TransactionType.BILL_PAYMENT
        TransactionCode.CASH -> TransactionType.CASH
        TransactionCode.CASHBACK -> TransactionType.CASHBACK
        TransactionCode.CHEQUE -> TransactionType.CHEQUE
        TransactionCode.DIRECT_DEBIT -> TransactionType.DIRECT_DEBIT
        TransactionCode.INTEREST -> TransactionType.INTEREST
        TransactionCode.PURCHASE -> TransactionType.PURCHASE
        TransactionCode.STANDING_ORDER -> TransactionType.STANDING_ORDER
        TransactionCode.TRANSFER -> TransactionType.TRANSFER
        TransactionCode.NULL -> null
    }
}
