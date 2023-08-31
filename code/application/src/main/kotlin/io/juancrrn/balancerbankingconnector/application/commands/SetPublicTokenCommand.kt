package io.juancrrn.balancerbankingconnector.application.commands

import jakarta.validation.constraints.NotNull
import java.util.UUID

data class SetPublicTokenCommand(
    @field:NotNull
    val userId: UUID?,
    @field:NotNull
    val institutionId: String?,
    @field:NotNull
    val publicToken: String?,
) : Command<Unit>
