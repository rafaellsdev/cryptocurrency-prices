package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val coins: List<SearchCoin>?
)

data class SearchCoin(
    val id: String?,
    val symbol: String?,
    val name: String?,
    @SerializedName("large")
    val image: String?
)
