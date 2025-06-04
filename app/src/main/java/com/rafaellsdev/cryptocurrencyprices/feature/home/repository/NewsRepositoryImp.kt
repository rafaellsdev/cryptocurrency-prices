package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.NewsArticle
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper.toNewsArticleList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val service: NewsService
) : NewsRepository {
    override suspend fun getNewsArticles(): List<NewsArticle> =
        withContext(Dispatchers.IO) {
            service.getNews().toNewsArticleList()
        }
}
