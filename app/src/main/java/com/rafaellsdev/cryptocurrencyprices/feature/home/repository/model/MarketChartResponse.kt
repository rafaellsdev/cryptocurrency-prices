package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model

import com.google.gson.annotations.SerializedName

data class MarketChartResponse(
    val prices: List<List<Double>>
)
