package io.juancrrn.balancerbankingconnector.application.commands

import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidLinkToken
import java.util.UUID
import javax.validation.constraints.NotNull

data class CreateLinkTokenCommand(
    @field:NotNull
    val userId: UUID?,
) : Command<PlaidLinkToken>
