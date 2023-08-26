package io.juancrrn.balancerbankingconnector.restserver.api.requests.ext

import io.juancrrn.balancerbankingconnector.application.commands.CreateLinkTokenCommand
import io.juancrrn.balancerbankingconnector.restserver.api.models.CreateLinkTokenRequest

fun CreateLinkTokenRequest.toCommand() = CreateLinkTokenCommand(
    userId = userId,
)
