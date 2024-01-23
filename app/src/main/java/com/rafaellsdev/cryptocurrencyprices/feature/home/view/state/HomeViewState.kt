package com.rafaellsdev.cryptocurrencyprices.feature.home.view.state

import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency

sealed class HomeViewState {

    object Loading : HomeViewState()
    data class Success(val currencies: List<Currency>) : HomeViewState()
    data class Failure (val errorMessage: String): HomeViewState()
}