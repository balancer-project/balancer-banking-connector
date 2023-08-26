package io.juancrrn.balancerbankingconnector.restserver.controllers

import io.juancrrn.balancerbankingconnector.restserver.api.BankLinkApi
import io.juancrrn.balancerbankingconnector.restserver.api.models.CreateLinkToken201Response
import io.juancrrn.balancerbankingconnector.restserver.api.models.CreateLinkTokenRequest
import io.juancrrn.balancerbankingconnector.restserver.api.models.SetPublicTokenRequest
import io.juancrrn.balancerbankingconnector.restserver.api.requests.ext.toCommand
import io.juancrrn.balancerbankingconnector.application.usecases.UseCaseDispatcher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BankLinkController(
    private val dispatcher: UseCaseDispatcher,
) : BankLinkApi {

    override suspend fun createLinkToken(
        createLinkTokenRequest: CreateLinkTokenRequest,
    ): ResponseEntity<CreateLinkToken201Response> {
        val output = dispatcher.dispatch(createLinkTokenRequest.toCommand())

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(CreateLinkToken201Response(output.token))
    }

    override suspend fun setPublicToken(setPublicTokenRequest: SetPublicTokenRequest): ResponseEntity<Unit> {
        dispatcher.dispatch(setPublicTokenRequest.toCommand())

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
