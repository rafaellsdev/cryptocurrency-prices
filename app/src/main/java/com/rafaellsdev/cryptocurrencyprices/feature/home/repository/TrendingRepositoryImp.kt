package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.TrendingCoin
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper.toTrendingCoinList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.TrendingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingRepositoryImp @Inject constructor(
    private val service: TrendingService
) : TrendingRepository {
    override suspend fun getTrendingCoins(): List<TrendingCoin> =
        withContext(Dispatchers.IO) {
            service.getTrendingCoins().toTrendingCoinList()
        }
}
