package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service

import com.rafaellsdev.cryptocurrencyprices.commons.const.URLs.CURRENCIES_SERVICE
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.CurrencyResponse
import retrofit2.http.GET

interface DiscoverService {
    @GET(CURRENCIES_SERVICE)
    suspend fun discoverCurrencies(): List<CurrencyResponse>
}