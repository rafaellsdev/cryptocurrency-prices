package com.rafaellsdev.cryptocurrencyprices.commons.model

/**
 * Represents a news article from crypto news sources.
 */
data class NewsArticle(
    val id: String,
    val title: String,
    val source: String,
    val url: String,
    val imageUrl: String
)
