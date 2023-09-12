package io.juancrrn.balancerbankingconnector.domain.exceptions

class TransactionIsIncomingException : DomainException(
    "transaction_is_incoming",
    "This transaction represents money moving in to the account.",
)
