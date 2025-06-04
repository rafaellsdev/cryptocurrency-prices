package com.rafaellsdev.cryptocurrencyprices.commons.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rafaellsdev.cryptocurrencyprices.commons.model.TrendingCoin

@Entity(tableName = "trending_coins")
data class TrendingCoinEntity(
    @PrimaryKey val id: String,
    val symbol: String,
    val name: String,
    val image: String
)

fun TrendingCoinEntity.toDomain() = TrendingCoin(
    id = id,
    symbol = symbol,
    name = name,
    image = image
)

fun TrendingCoin.toEntity() = TrendingCoinEntity(
    id = id,
    symbol = symbol,
    name = name,
    image = image
)
