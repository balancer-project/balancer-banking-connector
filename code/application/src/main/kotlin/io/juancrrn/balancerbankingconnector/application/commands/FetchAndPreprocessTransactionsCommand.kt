package io.juancrrn.balancerbankingconnector.application.commands

data class FetchAndPreprocessTransactionsCommand(
    val itemId: String?,
) : Command<Unit>
