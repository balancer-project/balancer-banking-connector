package io.juancrrn.balancerbankingconnector.domain.valueobjects

/**
 * The channel used to make a payment.
 */
enum class PaymentChannel {

    /**
     * Transactions that took place online.
     */
    ONLINE,

    /**
     * Transactions that were made at a physical location.
     */
    IN_STORE,

    /**
     * Transactions that relate to banks, e.g. fees or deposits.
     */
    OTHER,
}
