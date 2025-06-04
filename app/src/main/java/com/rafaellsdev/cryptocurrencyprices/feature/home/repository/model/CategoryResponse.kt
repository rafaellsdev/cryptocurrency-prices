package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    val id: String?,
    @SerializedName("name")
    val name: String?
)
