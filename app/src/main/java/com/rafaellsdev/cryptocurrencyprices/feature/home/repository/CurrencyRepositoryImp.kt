package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper.toCurrencyList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.DiscoverService
import com.rafaellsdev.cryptocurrencyprices.commons.currency.CurrencyPreferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepositoryImp @Inject constructor(
    private val discoverService: DiscoverService,
    private val currencyPreferenceRepository: CurrencyPreferenceRepository
) : CurrencyRepository {
    override suspend fun discoverCurrencies(category: String?): List<Currency> =
        withContext(Dispatchers.IO) {
            val currency = currencyPreferenceRepository.getSelectedCurrency()
            discoverService.discoverCurrencies(
                currency = currency,
                category = category
            ).toCurrencyList()
        }
}