package io.juancrrn.balancerbankingconnector.application.usecases

import io.juancrrn.balancerbankingconnector.application.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.application.commands.SetPublicTokenCommand
import io.juancrrn.balancerbankingconnector.domain.entities.PlaidItem
import io.juancrrn.balancerbankingconnector.domain.repositories.PlaidItemRepository
import io.juancrrn.balancerbankingconnector.domain.repositories.UserRepository
import io.juancrrn.balancerbankingconnector.domain.repositories.assertNotAlreadyLinked
import io.juancrrn.balancerbankingconnector.domain.valueobjects.InstitutionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidPublicToken
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId
import org.springframework.stereotype.Component

@Component
class SetPublicTokenUseCase(
    // private val validator: Validator,
    private val userRepository: UserRepository,
    private val plaidItemRepository: PlaidItemRepository,
) : CommandUseCase<SetPublicTokenCommand, Unit> {

    override suspend fun dispatch(command: SetPublicTokenCommand) {
        // TODO: validate command, error handling, etc.
        // validator.assertValid(command)

        val userId = UserId(command.userId!!)
        val institutionId = InstitutionId(command.institutionId!!)

        userRepository.assertExists(userId)

        plaidItemRepository.assertNotAlreadyLinked(userId, institutionId)

        val publicToken = PlaidPublicToken(command.publicToken!!)

        val (itemId, accessToken) = plaidItemRepository.exchangePublicToken(publicToken)

        plaidItemRepository.save(
            PlaidItem(
                itemId,
                userId,
                institutionId,
                accessToken,
            ),
        )
    }
}
