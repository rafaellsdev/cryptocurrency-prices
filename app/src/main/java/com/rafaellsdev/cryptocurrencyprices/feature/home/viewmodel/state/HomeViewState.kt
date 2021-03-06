package com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.state

import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency

sealed class HomeViewState {

    object Loading : HomeViewState()
    data class Success(val currencies: List<Currency>) : HomeViewState()
    object Failure : HomeViewState()
}