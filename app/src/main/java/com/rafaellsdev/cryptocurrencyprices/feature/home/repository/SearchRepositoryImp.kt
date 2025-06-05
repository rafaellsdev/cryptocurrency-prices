package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.SearchCoin
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper.toSearchCoinList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.SearchService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val service: SearchService
) : SearchRepository {
    override suspend fun searchCoins(query: String): List<SearchCoin> = withContext(Dispatchers.IO) {
        if (query.isBlank()) return@withContext emptyList()
        try {
            service.searchCoins(query).toSearchCoinList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
