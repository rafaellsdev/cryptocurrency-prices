package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.SearchCoin

interface SearchRepository {
    suspend fun searchCoins(query: String): List<SearchCoin>
}
