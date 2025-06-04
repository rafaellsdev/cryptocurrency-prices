package com.rafaellsdev.cryptocurrencyprices.commons.model

data class GlobalMetrics(
    val totalMarketCap: Double,
    val totalVolume: Double,
    val dominantCoins: Map<String, Double>
)
