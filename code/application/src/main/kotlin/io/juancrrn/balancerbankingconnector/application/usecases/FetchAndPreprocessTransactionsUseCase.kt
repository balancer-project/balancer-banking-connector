package io.juancrrn.balancerbankingconnector.application.usecases

import io.juancrrn.balancerbankingconnector.application.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.application.commands.FetchAndPreprocessTransactionsCommand
import io.juancrrn.balancerbankingconnector.domain.repositories.PlaidItemRepository
import io.juancrrn.balancerbankingconnector.domain.valueobjects.PlaidItemId

class FetchAndPreprocessTransactionsUseCase(
    private val plaidItemRepository: PlaidItemRepository
) : CommandUseCase<FetchAndPreprocessTransactionsCommand, Unit> {

    override suspend fun dispatch(command: FetchAndPreprocessTransactionsCommand) {
        // TODO: assert command is valid

        // TODO: what to do if item does not exist?
        val item = plaidItemRepository.find(PlaidItemId(command.itemId!!))!!

        if (item.nextCursor == null) {
            // Get the first cursor
        } else {
            // Fetch new transactions
            // Get new cursor
            // Launch new transactions preprocessing
        }
    }
}
