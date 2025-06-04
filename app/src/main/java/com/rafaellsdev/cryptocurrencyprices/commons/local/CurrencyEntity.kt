package com.rafaellsdev.cryptocurrencyprices.commons.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency

@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey val id: String,
    val symbol: String,
    val name: String?,
    val image: String?,
    val marketCap: Double,
    val currentPrice: Double,
    val priceChangePercentage: Double,
    val highPrice: Double,
    val lowPrice: Double,
    val totalVolume: Double,
    val circulatingSupply: Double,
    val totalSupply: Double
)

fun CurrencyEntity.toDomain() = Currency(
    id = id,
    symbol = symbol,
    name = name,
    image = image,
    marketCap = marketCap,
    currentPrice = currentPrice,
    priceChangePercentage = priceChangePercentage,
    highPrice = highPrice,
    lowPrice = lowPrice,
    totalVolume = totalVolume,
    circulatingSupply = circulatingSupply,
    totalSupply = totalSupply
)

fun Currency.toEntity() = CurrencyEntity(
    id = id,
    symbol = symbol,
    name = name,
    image = image,
    marketCap = marketCap,
    currentPrice = currentPrice,
    priceChangePercentage = priceChangePercentage,
    highPrice = highPrice,
    lowPrice = lowPrice,
    totalVolume = totalVolume,
    circulatingSupply = circulatingSupply,
    totalSupply = totalSupply
)
