package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.converter

import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.ConverterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyConverterRepositoryImp @Inject constructor(
    private val converterService: ConverterService
) : CurrencyConverterRepository {
    override suspend fun convert(id: String, currencies: List<String>): Map<String, Double> =
        withContext(Dispatchers.IO) {
            val response = converterService.convertCurrency(
                ids = id,
                vsCurrencies = currencies.joinToString(",")
            )
            response[id] ?: emptyMap()
        }
}
