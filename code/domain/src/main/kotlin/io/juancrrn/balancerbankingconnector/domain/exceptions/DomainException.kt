package io.juancrrn.balancerbankingconnector.domain.exceptions

abstract class DomainException(
    val code: String,
    message: String,
) : Exception(message)
