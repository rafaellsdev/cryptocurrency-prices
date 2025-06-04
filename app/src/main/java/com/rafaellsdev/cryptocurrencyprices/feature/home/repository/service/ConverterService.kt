package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service

import com.rafaellsdev.cryptocurrencyprices.commons.const.URLs.CONVERTER_SERVICE
import retrofit2.http.GET
import retrofit2.http.Query

interface ConverterService {
    @GET(CONVERTER_SERVICE)
    suspend fun convertCurrency(
        @Query("ids") ids: String,
        @Query("vs_currencies") vsCurrencies: String
    ): Map<String, Map<String, Double>>
}
