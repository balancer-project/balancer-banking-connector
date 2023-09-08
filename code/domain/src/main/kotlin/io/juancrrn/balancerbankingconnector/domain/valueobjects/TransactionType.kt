package io.juancrrn.balancerbankingconnector.domain.valueobjects

/**
 * The type of transaction.
 */
enum class TransactionType {

    /**
     * Bank adjustment.
     */
    ADJUSTMENT,

    /**
     * Cash deposit or withdrawal via an automated teller machine.
     */
    ATM,

    /**
     * Charge or fee levied by the institution.
     */
    BANK_CHARGE,

    /**
     * Payment of a bill.
     */
    BILL_PAYMENT,

    /**
     * Cash deposit or withdrawal.
     */
    CASH,

    /**
     * Cash withdrawal while making a debit card purchase.
     */
    CASHBACK,

    /**
     * Document ordering the payment of money to another person or organization.
     */
    CHEQUE,

    /**
     * Automatic withdrawal of funds initiated by a third party at a regular interval.
     */
    DIRECT_DEBIT,

    /**
     * Interest earned or incurred.
     */
    INTEREST,

    /**
     * Purchase made with a debit or credit card.
     */
    PURCHASE,

    /**
     * Payment instructed by the account holder to a third party at a regular interval.
     */
    STANDING_ORDER,

    /**
     * Transfer of money between accounts.
     */
    TRANSFER,
}
