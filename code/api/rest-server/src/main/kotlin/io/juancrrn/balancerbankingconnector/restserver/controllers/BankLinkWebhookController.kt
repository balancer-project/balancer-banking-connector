package io.juancrrn.balancerbankingconnector.restserver.controllers

import io.juancrrn.balancerbankingconnector.application.usecases.UseCaseDispatcher
import io.juancrrn.balancerbankingconnector.restserver.api.BankLinkWebhookApi
import io.juancrrn.balancerbankingconnector.restserver.api.models.NotifyUpdateRequestBody
import io.juancrrn.balancerbankingconnector.restserver.api.requests.ext.toCommand
import org.slf4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BankLinkWebhookController(
    private val dispatcher: UseCaseDispatcher,
    private val logger: Logger,
) : BankLinkWebhookApi {

    override suspend fun notifyUpdate(notifyUpdateRequestBody: NotifyUpdateRequestBody): ResponseEntity<Unit> {
        logger.info("Plaid webhook was called")
        dispatcher.dispatch(notifyUpdateRequestBody.toCommand())

        return ResponseEntity.ok().build()
    }
}
