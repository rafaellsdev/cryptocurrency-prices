package com.rafaellsdev.cryptocurrencyprices.commons.currency

import android.content.SharedPreferences
import javax.inject.Inject

class CurrencyPreferenceRepositoryImp @Inject constructor(
    private val preferences: SharedPreferences
) : CurrencyPreferenceRepository {

    private val key = "selected_currency"
    private val defaultCurrency = "eur"

    override fun getSelectedCurrency(): String =
        preferences.getString(key, defaultCurrency) ?: defaultCurrency

    override fun setSelectedCurrency(currency: String) {
        preferences.edit().putString(key, currency).apply()
    }
}
