package io.juancrrn.balancerbankingconnector.infrastructure.repository.models.ext

import io.juancrrn.balancerbankingconnector.domain.valueobjects.TransactionDetailedCategory

fun getDetailedPersonalFinanceCategory(category: String): TransactionDetailedCategory {
    return TransactionDetailedCategory.values().firstOrNull { it.name == category }
        ?: throw IllegalArgumentException("Invalid detailed category: $category")
}
