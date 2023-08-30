package io.juancrrn.balancerbankingconnector.application.commands

import io.juancrrn.balancerbankingconnector.domain.entities.PlaidTransaction
import io.juancrrn.balancerbankingconnector.domain.valueobjects.InstitutionId
import io.juancrrn.balancerbankingconnector.domain.valueobjects.UserId

data class PreprocessAndNotifyTransactionAddedCommand(
    val userId: UserId,
    val institutionId: InstitutionId,
    val plaidTransaction: PlaidTransaction,
) : Command<Unit>
