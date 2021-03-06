package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency

interface CurrencyRepository {
    suspend fun discoverCurrencies(): List<Currency>
}