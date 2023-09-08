package io.juancrrn.balancerbankingconnector.infrastructure.repository.models.ext

import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionPrimaryCategory

fun getPrimaryPersonalFinanceCategory(category: String): TransactionPrimaryCategory {
    return TransactionPrimaryCategory.values().firstOrNull { it.name == category }
        ?: TransactionPrimaryCategory.OTHER
}
