package io.juancrrn.balancerbankingconnector.domain.validators

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validator

fun Validator.assertValid(entity: Any) {
    validate(entity).apply {
        if (isNotEmpty()) {
            throw ConstraintViolationException(this)
        }
    }
}
