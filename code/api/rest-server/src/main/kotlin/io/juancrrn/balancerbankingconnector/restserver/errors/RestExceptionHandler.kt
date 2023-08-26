package io.juancrrn.balancerbankingconnector.restserver.errors

import io.juancrrn.balancerbankingconnector.domain.exceptions.DomainException
import io.juancrrn.balancerbankingconnector.domain.exceptions.InvalidPlaidPublicTokenException
import io.juancrrn.balancerbankingconnector.domain.exceptions.UserAndInstitutionAlreadyLinkedException
import io.juancrrn.balancerbankingconnector.restserver.api.models.Error
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import kotlin.reflect.full.superclasses

@RestControllerAdvice
class RestExceptionHandler(
    private val logger: Logger,
) {

    @ExceptionHandler(DomainException::class)
    fun handle(ex: DomainException): ResponseEntity<Error> {
        logger.debug(ex.message, ex)
        return ResponseEntity.status(ex.status()).body(Error(ex.code, ex.message))
    }

    @ExceptionHandler(Exception::class)
    fun handle(ex: Exception): ResponseEntity<Error> {
        logger.error(ex.message, ex)
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(Error("internal_server_error", ex.message))
    }

    private fun DomainException.status(): HttpStatus {
        return DOMAIN_EXCEPTION_STATUSES.firstNotNullOfOrNull { entry ->
            if (entry.value.contains(this::class) || this::class.superclasses.any { entry.value.contains(it) }) {
                entry.key
            } else {
                null
            }
        } ?: INTERNAL_SERVER_ERROR
    }

    companion object {

        private val DOMAIN_EXCEPTION_STATUSES = mapOf(
            CONFLICT to listOf(
                UserAndInstitutionAlreadyLinkedException::class,
            ),
            BAD_REQUEST to listOf(
                InvalidPlaidPublicTokenException::class,
            ),
        )
    }
}
