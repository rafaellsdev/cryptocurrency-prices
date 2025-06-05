package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency

interface CurrencyRepository {
    suspend fun discoverCurrencies(category: String? = null): List<Currency>

    suspend fun getCurrenciesByIds(ids: List<String>): List<Currency>
}