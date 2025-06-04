package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper

import com.rafaellsdev.cryptocurrencyprices.commons.model.NewsArticle
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.NewsResponse

fun NewsResponse.toNewsArticleList(): List<NewsArticle> =
    this.data?.mapNotNull {
        val id = it.id ?: return@mapNotNull null
        val title = it.title ?: return@mapNotNull null
        NewsArticle(
            id = id,
            title = title,
            source = it.sourceName.orEmpty(),
            url = it.url.orEmpty(),
            imageUrl = it.imageUrl.orEmpty()
        )
    } ?: emptyList()
