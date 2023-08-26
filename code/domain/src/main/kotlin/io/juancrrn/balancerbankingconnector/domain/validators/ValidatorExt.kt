package io.juancrrn.balancerbankingconnector.domain.validators

import javax.validation.ConstraintViolationException
import javax.validation.Validator

fun Validator.assertValid(entity: Any) {
    validate(entity).apply {
        if (isNotEmpty()) {
            throw ConstraintViolationException(this)
        }
    }
}
