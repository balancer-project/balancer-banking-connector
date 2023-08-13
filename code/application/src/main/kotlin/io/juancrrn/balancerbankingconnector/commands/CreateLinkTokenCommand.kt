package io.juancrrn.balancerbankingconnector.commands

import io.juancrrn.balancerbankingconnector.valueobjects.LinkToken
import java.util.*

data class CreateLinkTokenCommand(
    val userId: UUID,
) : Command<LinkToken>
