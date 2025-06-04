package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.converter

interface CurrencyConverterRepository {
    suspend fun convert(id: String, currencies: List<String>): Map<String, Double>
}
