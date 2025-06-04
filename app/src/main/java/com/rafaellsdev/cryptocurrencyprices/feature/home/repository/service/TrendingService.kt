package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service

import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.TrendingResponse
import retrofit2.http.GET

interface TrendingService {
    @GET("search/trending")
    suspend fun getTrendingCoins(): TrendingResponse
}
