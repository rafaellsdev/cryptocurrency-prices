package com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaellsdev.cryptocurrencyprices.commons.ext.emit
import com.rafaellsdev.cryptocurrencyprices.commons.ext.safeLaunch
import com.rafaellsdev.cryptocurrencyprices.commons.model.DefaultError
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PriceChartViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val mutableChartData = MutableLiveData<List<Pair<Long, Double>>>()
    val chartData: LiveData<List<Pair<Long, Double>>> = mutableChartData

    fun loadChart(currencyId: String, days: Int) = safeLaunch(::handleError) {
        val data = currencyRepository.getMarketChart(currencyId, days)
        mutableChartData.emit(data)
    }

    private fun handleError(error: DefaultError) {
        // no-op for simplicity
    }
}
