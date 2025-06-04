package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.GlobalMetrics

interface GlobalMetricsRepository {
    suspend fun getGlobalMetrics(): GlobalMetrics
}
