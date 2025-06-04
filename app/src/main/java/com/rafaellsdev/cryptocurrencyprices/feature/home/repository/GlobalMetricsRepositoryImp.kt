package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.currency.CurrencyPreferenceRepository
import com.rafaellsdev.cryptocurrencyprices.commons.model.GlobalMetrics
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.GlobalMetricsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GlobalMetricsRepositoryImp @Inject constructor(
    private val service: GlobalMetricsService,
    private val currencyPreferenceRepository: CurrencyPreferenceRepository
) : GlobalMetricsRepository {
    override suspend fun getGlobalMetrics(): GlobalMetrics = withContext(Dispatchers.IO) {
        val response = service.getGlobalMetrics()
        val currency = currencyPreferenceRepository.getSelectedCurrency()
        val data = response.data
        val marketCap = data?.totalMarketCap?.get(currency) ?: 0.0
        val volume = data?.totalVolume?.get(currency) ?: 0.0
        val dominance = data?.marketCapPercentage ?: emptyMap()
        GlobalMetrics(marketCap, volume, dominance)
    }
}
