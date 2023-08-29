package io.juancrrn.balancerbankingconnector.domain.valueobjects

enum class PlaidEnvironment {

    SANDBOX,
    DEVELOPMENT,
    PRODUCTION,
    ;

    companion object {

        fun fromString(environment: String): PlaidEnvironment {
            return values().firstOrNull() { it.name.equals(environment, ignoreCase = true) }
                ?: throw IllegalArgumentException("Invalid Plaid environment: $environment")
        }
    }
}
