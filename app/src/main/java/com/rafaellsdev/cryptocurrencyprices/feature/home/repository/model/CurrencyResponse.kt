package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    val id: String?,
    val symbol: String?,
    val name: String?,
    val image: String?,
    @SerializedName("market_cap")
    val marketCap: Double?,
    @SerializedName("current_price")
    val currentPrice: Double?,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage: Double?,
    @SerializedName("high_24h")
    val highPrice: Double?,
    @SerializedName("low_24h")
    val lowPrice: Double?
)