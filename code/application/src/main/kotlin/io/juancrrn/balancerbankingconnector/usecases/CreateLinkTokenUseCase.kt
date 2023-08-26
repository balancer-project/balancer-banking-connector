package io.juancrrn.balancerbankingconnector.usecases

import io.juancrrn.balancerbankingconnector.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.commands.CreateLinkTokenCommand
import io.juancrrn.balancerbankingconnector.repositories.PlaidLinkTokenRepository
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidLinkToken
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
import org.springframework.stereotype.Component

@Component
class CreateLinkTokenUseCase(
    // private val validator: Validator,
    private val plaidLinkTokenRepository: PlaidLinkTokenRepository,
) : CommandUseCase<CreateLinkTokenCommand, PlaidLinkToken> {

    override suspend fun dispatch(command: CreateLinkTokenCommand): PlaidLinkToken {
        // TODO: validate command, error handling, etc.
        // validator.assertValid(command)

        return plaidLinkTokenRepository.create(UserId(command.userId!!))
    }
}
