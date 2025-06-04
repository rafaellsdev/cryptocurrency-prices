package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.TrendingCoin

interface TrendingRepository {
    suspend fun getTrendingCoins(): List<TrendingCoin>
}
