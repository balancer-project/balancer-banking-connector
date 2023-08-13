package io.juancrrn.balancerbankingconnector.restserver.api.requests.ext

import io.juancrrn.balancerbankingconnector.commands.SetPublicTokenCommand
import io.juancrrn.balancerbankingconnector.restserver.api.models.SetPublicTokenRequest

fun SetPublicTokenRequest.toCommand() = SetPublicTokenCommand(
    userId = userId,
    publicToken = publicToken,
)
