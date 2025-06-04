package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.CoinCategory

interface CategoryRepository {
    suspend fun getCategories(): List<CoinCategory>
}
