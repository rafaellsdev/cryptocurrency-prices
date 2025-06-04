package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model

import com.google.gson.annotations.SerializedName

/**
 * Response from the CryptoCompare news endpoint.
 */
data class NewsResponse(
    @SerializedName("Data")
    val data: List<NewsArticleResponse>?
)

data class NewsArticleResponse(
    val id: String?,
    val title: String?,
    val url: String?,
    @SerializedName("source")
    val sourceName: String?,
    @SerializedName("imageurl")
    val imageUrl: String?
)
