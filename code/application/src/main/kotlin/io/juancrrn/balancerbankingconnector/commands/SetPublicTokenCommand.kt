package io.juancrrn.balancerbankingconnector.commands

import java.util.*
import javax.validation.constraints.NotNull

data class SetPublicTokenCommand(
    @field:NotNull
    val userId: UUID?,
    @field:NotNull
    val publicToken: String?,
) : Command<Unit>
