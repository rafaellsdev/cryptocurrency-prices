package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.commons.local.CurrencyDao
import com.rafaellsdev.cryptocurrencyprices.commons.local.toDomain
import com.rafaellsdev.cryptocurrencyprices.commons.local.toEntity
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper.toCurrencyList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.DiscoverService
import com.rafaellsdev.cryptocurrencyprices.commons.currency.CurrencyPreferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CurrencyRepositoryImp @Inject constructor(
    private val discoverService: DiscoverService,
    private val currencyPreferenceRepository: CurrencyPreferenceRepository,
    private val currencyDao: CurrencyDao
) : CurrencyRepository {
    override suspend fun discoverCurrencies(category: String?): List<Currency> =
        withContext(Dispatchers.IO) {
            val currency = currencyPreferenceRepository.getSelectedCurrency()
            try {
                val result = discoverService.discoverCurrencies(
                    currency = currency,
                    category = category
                ).toCurrencyList()
                currencyDao.insertCurrencies(result.map { it.toEntity() })
                result
            } catch (e: Exception) {
                if (e is IOException || (e is HttpException && e.code() == 429)) {
                    currencyDao.getCurrencies().map { it.toDomain() }
                } else {
                    throw e
                }
            }
        }
}