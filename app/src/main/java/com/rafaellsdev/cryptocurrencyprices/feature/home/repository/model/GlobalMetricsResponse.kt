package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model

import com.google.gson.annotations.SerializedName

data class GlobalMetricsResponse(
    val data: GlobalMetricsData?
)

data class GlobalMetricsData(
    @SerializedName("total_market_cap")
    val totalMarketCap: Map<String, Double>?,
    @SerializedName("total_volume")
    val totalVolume: Map<String, Double>?,
    @SerializedName("market_cap_percentage")
    val marketCapPercentage: Map<String, Double>?
)
