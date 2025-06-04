package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.TrendingCoin
import com.rafaellsdev.cryptocurrencyprices.commons.local.TrendingCoinDao
import com.rafaellsdev.cryptocurrencyprices.commons.local.toDomain
import com.rafaellsdev.cryptocurrencyprices.commons.local.toEntity
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper.toTrendingCoinList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.TrendingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TrendingRepositoryImp @Inject constructor(
    private val service: TrendingService,
    private val trendingCoinDao: TrendingCoinDao
) : TrendingRepository {
    override suspend fun getTrendingCoins(): List<TrendingCoin> =
        withContext(Dispatchers.IO) {
            try {
                val result = service.getTrendingCoins().toTrendingCoinList()
                trendingCoinDao.insertTrendingCoins(result.map { it.toEntity() })
                result
            } catch (e: Exception) {
                if (e is IOException || (e is HttpException && e.code() == 429)) {
                    trendingCoinDao.getTrendingCoins().map { it.toDomain() }
                } else {
                    throw e
                }
            }
        }
}
