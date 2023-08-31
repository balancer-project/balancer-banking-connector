package io.juancrrn.balancerbankingconnector.application.usecases

import io.juancrrn.balancerbankingconnector.application.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.application.commands.CreateLinkTokenCommand
import io.juancrrn.balancerbankingconnector.domain.repositories.PlaidLinkTokenRepository
import io.juancrrn.balancerbankingconnector.domain.validators.assertValid
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidLinkToken
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import jakarta.validation.Validator
import org.springframework.stereotype.Component

@Component
class CreateLinkTokenUseCase(
    private val validator: Validator,
    private val plaidLinkTokenRepository: PlaidLinkTokenRepository,
) : CommandUseCase<CreateLinkTokenCommand, PlaidLinkToken> {

    override suspend fun dispatch(command: CreateLinkTokenCommand): PlaidLinkToken {
        validator.assertValid(command)

        return plaidLinkTokenRepository.create(UserId(command.userId!!))
    }
}
