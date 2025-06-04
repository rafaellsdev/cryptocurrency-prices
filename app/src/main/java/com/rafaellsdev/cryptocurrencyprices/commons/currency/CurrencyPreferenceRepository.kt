package com.rafaellsdev.cryptocurrencyprices.commons.currency

interface CurrencyPreferenceRepository {
    fun getSelectedCurrency(): String
    fun setSelectedCurrency(currency: String)
}
