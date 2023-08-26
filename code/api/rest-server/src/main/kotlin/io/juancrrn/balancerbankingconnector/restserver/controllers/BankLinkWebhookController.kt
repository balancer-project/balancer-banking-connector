package io.juancrrn.balancerbankingconnector.restserver.controllers

import io.juancrrn.balancerbankingconnector.restserver.api.BankLinkWebhookApi
import io.juancrrn.balancerbankingconnector.restserver.api.models.NotifyUpdateRequestBody
import io.juancrrn.balancerbankingconnector.restserver.api.requests.ext.toCommand
import io.juancrrn.balancerbankingconnector.application.usecases.UseCaseDispatcher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BankLinkWebhookController(
    private val dispatcher: UseCaseDispatcher,
) : BankLinkWebhookApi {

    override suspend fun notifyUpdate(notifyUpdateRequestBody: NotifyUpdateRequestBody): ResponseEntity<Unit> {
        dispatcher.dispatch(notifyUpdateRequestBody.toCommand())

        return ResponseEntity.ok().build()
    }
}
