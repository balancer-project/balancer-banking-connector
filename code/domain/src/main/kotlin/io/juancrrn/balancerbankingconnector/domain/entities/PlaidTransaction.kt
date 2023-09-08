package io.juancrrn.balancerbankingconnector.domain.entities

import io.juancrrn.balancerbankingconnector.domain.valueobjects.PaymentChannel
import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionDetailedCategory
import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionPrimaryCategory
import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionType
import java.time.LocalDate
import java.time.LocalDateTime

data class PlaidTransaction(
    val id: TransactionId,
    val accountId: String,
    val amount: Double,
    val isoCurrencyCode: String?,
    val category: List<String>?,
    val categoryId: String?,
    val name: String,
    val merchantName: String?,
    val pending: Boolean,
    val pendingTransactionId: TransactionId?,
    val authorizedDate: LocalDate?,
    val authorizedDateTime: LocalDateTime?,
    val date: LocalDate,
    val dateTime: LocalDateTime?,
    val paymentChannel: PaymentChannel,
    val primaryPersonalFinanceCategory: TransactionPrimaryCategory?,
    val detailedPersonalFinanceCategory: TransactionDetailedCategory?,
    val type: TransactionType?,
)
