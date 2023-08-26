package io.juancrrn.balancerbankingconnector.restserver.api.requests.ext

import io.juancrrn.balancerbankingconnector.application.commands.SetPublicTokenCommand
import io.juancrrn.balancerbankingconnector.restserver.api.models.SetPublicTokenRequest

fun SetPublicTokenRequest.toCommand(): SetPublicTokenCommand {
    return SetPublicTokenCommand(
        userId = userId,
        institutionId = institutionId,
        publicToken = publicToken,
    )
}
