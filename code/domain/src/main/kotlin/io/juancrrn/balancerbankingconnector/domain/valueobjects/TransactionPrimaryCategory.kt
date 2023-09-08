package io.juancrrn.balancerbankingconnector.domain.valueobjects

/**
 * The primary category of a transaction.
 */
enum class TransactionPrimaryCategory {

    TRANSFER_OUT,
    LOAN_PAYMENTS,
    BANK_FEES,
    ENTERTAINMENT,
    FOOD_AND_DRINK,
    GENERAL_MERCHANDISE,
    HOME_IMPROVEMENT,
    MEDICAL,
    PERSONAL_CARE,
    GENERAL_SERVICES,
    GOVERNMENT_AND_NON_PROFIT,
    TRANSPORTATION,
    TRAVEL,
    RENT_AND_UTILITIES,
    OTHER,
}
