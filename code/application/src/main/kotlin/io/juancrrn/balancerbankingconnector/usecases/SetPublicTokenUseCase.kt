package io.juancrrn.balancerbankingconnector.usecases

import io.juancrrn.balancerbankingconnector.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.commands.SetPublicTokenCommand
import io.juancrrn.balancerbankingconnector.repositories.ItemRepository
import io.juancrrn.balancerbankingconnector.repositories.UserRepository
import io.juancrrn.balancerbankingconnector.valueobjects.PublicToken
import org.springframework.stereotype.Component

@Component
class SetPublicTokenUseCase(
    // private val validator: Validator,
    private val userRepository: UserRepository,
    private val itemRepository: ItemRepository,
) : CommandUseCase<SetPublicTokenCommand, Unit> {

    override suspend fun dispatch(command: SetPublicTokenCommand) {
        // TODO: validate command, error handling, etc.
        // validator.assertValid(command)

        userRepository.assertExists(command.userId!!)

        val publicToken = PublicToken(command.publicToken!!)

        val (itemId, accessToken) = itemRepository.exchangePublicToken(publicToken)

        userRepository.associateItem(command.userId, itemId, accessToken)
    }
}
