package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service

import com.rafaellsdev.cryptocurrencyprices.commons.const.URLs.DISCOVER
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.CurrencyResponse
import retrofit2.http.GET

interface DiscoverService {
    @GET(DISCOVER)
    suspend fun discoverCurrencies(): List<CurrencyResponse>
}