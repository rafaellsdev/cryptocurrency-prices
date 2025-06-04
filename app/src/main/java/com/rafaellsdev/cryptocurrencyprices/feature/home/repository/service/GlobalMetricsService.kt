package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service

import com.rafaellsdev.cryptocurrencyprices.commons.const.URLs.GLOBAL_SERVICE
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.GlobalMetricsResponse
import retrofit2.http.GET

interface GlobalMetricsService {
    @GET(GLOBAL_SERVICE)
    suspend fun getGlobalMetrics(): GlobalMetricsResponse
}
