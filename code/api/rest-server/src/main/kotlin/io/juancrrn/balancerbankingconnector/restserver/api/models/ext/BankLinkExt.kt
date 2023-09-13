package io.juancrrn.balancerbankingconnector.restserver.api.models.ext

import io.juancrrn.balancerbankingconnector.domain.entities.PlaidItem
import io.juancrrn.balancerbankingconnector.restserver.api.models.BankLink
import java.time.ZoneOffset

fun PlaidItem.toBankLinkModel(): BankLink {
    return BankLink(
        id = id.id,
        userId = userId.id,
        institutionId = institutionId.id,
        createdAt = createdAt.atOffset(ZoneOffset.UTC),
    )
}
