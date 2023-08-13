package io.juancrrn.balancerbankingconnector.usecases

import io.juancrrn.balancerbankingconnector.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.commands.CreateLinkTokenCommand
import io.juancrrn.balancerbankingconnector.repositories.LinkTokenRepository
import io.juancrrn.balancerbankingconnector.valueobjects.LinkToken
import org.springframework.stereotype.Component

@Component
class CreateLinkTokenUseCase(
    private val linkTokenRepository: LinkTokenRepository,
) : CommandUseCase<CreateLinkTokenCommand, LinkToken> {

    override suspend fun dispatch(command: CreateLinkTokenCommand): LinkToken {
        // TODO: validate command, error handling, etc.

        return linkTokenRepository.create(command.userId)
    }
}
