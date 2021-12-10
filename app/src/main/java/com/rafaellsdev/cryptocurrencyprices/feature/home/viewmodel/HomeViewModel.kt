package com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaellsdev.cryptocurrencyprices.commons.ext.emit
import com.rafaellsdev.cryptocurrencyprices.commons.ext.safeLaunch
import com.rafaellsdev.cryptocurrencyprices.commons.model.DefaultError
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.state.HomeViewState

const val TAG = "HomeViewModel"

class HomeViewModel @ViewModelInject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val mutableLiveDataState = MutableLiveData<HomeViewState>()
    val homeViewState: LiveData<HomeViewState> = mutableLiveDataState

    fun discoverCurrencies() = safeLaunch(::handleError) {
        mutableLiveDataState.emit(HomeViewState.Loading)

        val currencies = currencyRepository.discoverCurrencies()
        mutableLiveDataState.value = HomeViewState.Success(currencies)
    }

    private fun handleError(error: DefaultError) {
        Log.e(TAG, error.errorMessage)
        mutableLiveDataState.emit(HomeViewState.Failure)
    }
}