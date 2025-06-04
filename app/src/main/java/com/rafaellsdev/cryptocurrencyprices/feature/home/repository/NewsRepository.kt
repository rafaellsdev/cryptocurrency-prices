package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.NewsArticle

interface NewsRepository {
    suspend fun getNewsArticles(): List<NewsArticle>
}
