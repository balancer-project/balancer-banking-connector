package io.juancrrn.balancerbankingconnector.domain.valueobjects

enum class PlaidWebhookCode {

    SYNC_UPDATES_AVAILABLE,
    RECURRENT_TRANSACTIONS_UPDATE,
    INITIAL_UPDATE,
    HISTORICAL_UPDATE,
    DEFAULT_UPDATE,
    TRANSACTIONS_REMOVED,
}
