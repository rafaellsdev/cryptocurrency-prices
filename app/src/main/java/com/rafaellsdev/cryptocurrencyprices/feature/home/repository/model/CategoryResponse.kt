package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    val id: String?,
    val name: String?,
    @SerializedName("market_cap")
    val marketCap: Double?
)
