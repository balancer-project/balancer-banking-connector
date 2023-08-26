package io.juancrrn.balancerbankingconnector.usecases

import io.juancrrn.balancerbankingconnector.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.commands.SetPublicTokenCommand
import io.juancrrn.balancerbankingconnector.entities.PlaidItem
import io.juancrrn.balancerbankingconnector.repositories.PlaidItemRepository
import io.juancrrn.balancerbankingconnector.repositories.UserRepository
import io.juancrrn.balancerbankingconnector.repositories.assertNotAlreadyLinked
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidInstitutionId
import io.juancrrn.balancerbankingconnector.valueobjects.PlaidPublicToken
import io.juancrrn.balancerbankingconnector.valueobjects.UserId
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
        val institutionId = PlaidInstitutionId(command.institutionId!!)

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
