package io.juancrrn.balancerbankingconnector.domain.exceptions

import io.juancrrn.balancerbankingconnector.valueobjects.PlaidPublicToken

class InvalidPlaidPublicTokenException(
    val publicToken: PlaidPublicToken,
) : DomainException(
    "invalid_plaid_public_token",
    "The provided Plaid public token \"${publicToken.token}\" is invalid.",
)
