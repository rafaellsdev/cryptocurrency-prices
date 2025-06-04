package com.rafaellsdev.cryptocurrencyprices.commons.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rafaellsdev.cryptocurrencyprices.commons.model.CoinCategory

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val marketCap: Double
)

fun CategoryEntity.toDomain() = CoinCategory(
    id = id,
    name = name,
    marketCap = marketCap
)

fun CoinCategory.toEntity() = CategoryEntity(
    id = id,
    name = name,
    marketCap = marketCap
)
