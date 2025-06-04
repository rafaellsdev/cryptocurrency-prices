package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service

import com.rafaellsdev.cryptocurrencyprices.commons.const.URLs.CURRENCIES_SERVICE
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.CurrencyResponse
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.MarketChartResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DiscoverService {
    @GET(CURRENCIES_SERVICE)
    suspend fun discoverCurrencies(
        @Query("vs_currency") currency: String = "eur",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") maxPerPage: Int = 100,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false
    ): List<CurrencyResponse>

    @GET("coins/{id}/market_chart")
    suspend fun getMarketChart(
        @Path("id") id: String,
        @Query("vs_currency") currency: String = "eur",
        @Query("days") days: Int
    ): MarketChartResponse
}
