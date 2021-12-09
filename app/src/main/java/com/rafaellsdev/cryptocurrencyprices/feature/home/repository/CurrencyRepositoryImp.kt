package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper.toCurrencyList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.DiscoverService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepositoryImp @Inject constructor(
    private val discoverService: DiscoverService
) : CurrencyRepository {
    override suspend fun discoverCurrencies(): List<Currency> =
        withContext(Dispatchers.IO) {
            discoverService.discoverCurrencies().toCurrencyList()
        }
}