package io.juancrrn.balancerbankingconnector.application.commands

import java.util.UUID
import javax.validation.constraints.NotNull

data class SetPublicTokenCommand(
    @field:NotNull
    val userId: UUID?,
    @field:NotNull
    val institutionId: String?,
    @field:NotNull
    val publicToken: String?,
) : Command<Unit>
