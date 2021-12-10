package com.rafaellsdev.cryptocurrencyprices.commons.model

data class Currency(
    val id: String,
    val symbol: String,
    val name: String?,
    val image: String?,
    val currentPrice: Double,
    val priceChangePercentage: Double,
    val highPrice: Double,
    val lowPrice: Double
)