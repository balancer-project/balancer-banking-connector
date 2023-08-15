package io.juancrrn.balancerbankingconnector.commands

import io.juancrrn.balancerbankingconnector.valueobjects.LinkToken
import java.util.UUID
import javax.validation.constraints.NotNull

data class CreateLinkTokenCommand(
    @field:NotNull
    val userId: UUID?,
) : Command<LinkToken>
