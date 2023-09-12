package io.juancrrn.balancerbankingconnector.infrastructure.eventpublishers.payloads

data class TransactionAdded(
    val transaction: Transaction,
) {

    data class Transaction(
        val id: String,
        val userId: String,
        val institutionId: String,
        val accountId: String,
        val amount: Double,
        val currency: String?,
        val name: String,
        val merchantName: String?,
        val pending: Boolean,
        val pendingTransactionId: String?,
        val authorizedDate: String?,
        val authorizedDateTime: String?,
        val date: String,
        val dateTime: String?,
        val paymentChannel: String,
        val primaryPersonalFinanceCategory: String?,
        val detailedPersonalFinanceCategory: String?,
        val type: String?,
    )
}
