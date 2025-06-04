package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model

import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    val coins: List<TrendingCoinContainer>?
)

data class TrendingCoinContainer(
    val item: TrendingCoinItem?
)

data class TrendingCoinItem(
    val id: String?,
    val symbol: String?,
    val name: String?,
    @SerializedName("large")
    val image: String?
)
