package io.juancrrn.balancerbankingconnector.application.commands

import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidLinkToken
import jakarta.validation.constraints.NotNull
import java.util.UUID

data class CreateLinkTokenCommand(
    @field:NotNull
    val userId: UUID?,
) : Command<PlaidLinkToken>
