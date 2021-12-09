package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("symbol")
    val symbol: String?
)