package com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaellsdev.cryptocurrencyprices.commons.ext.emit
import com.rafaellsdev.cryptocurrencyprices.commons.ext.safeLaunch
import com.rafaellsdev.cryptocurrencyprices.commons.model.DefaultError
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.TrendingRepository
import com.rafaellsdev.cryptocurrencyprices.commons.favorites.FavoritesRepository
import com.rafaellsdev.cryptocurrencyprices.commons.currency.CurrencyPreferenceRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.state.HomeViewState
import com.rafaellsdev.cryptocurrencyprices.commons.model.TrendingCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val trendingRepository: TrendingRepository,
    private val favoritesRepository: FavoritesRepository,
    private val currencyPreferenceRepository: CurrencyPreferenceRepository
) : ViewModel() {

    private val mutableLiveDataState = MutableLiveData<HomeViewState>()
    val homeViewState: LiveData<HomeViewState> = mutableLiveDataState

    private val mutableTrending = MutableLiveData<List<TrendingCoin>>()
    val trendingCoins: LiveData<List<TrendingCoin>> = mutableTrending

    fun toggleFavorite(id: String) {
        favoritesRepository.toggleFavorite(id)
    }

    fun isFavorite(id: String): Boolean = favoritesRepository.isFavorite(id)

    fun setFiatCurrency(currency: String) {
        currencyPreferenceRepository.setSelectedCurrency(currency)
    }

    fun getFiatCurrency(): String = currencyPreferenceRepository.getSelectedCurrency()

    fun discoverCurrencies() = safeLaunch(::handleError) {
        mutableLiveDataState.emit(HomeViewState.Loading)

        val currencies = currencyRepository.discoverCurrencies()
        mutableLiveDataState.emit(HomeViewState.Success(currencies))
    }

    fun loadTrendingCoins() = safeLaunch(::handleError) {
        val trending = trendingRepository.getTrendingCoins()
        mutableTrending.emit(trending)
    }

    private fun handleError(error: DefaultError) {
        mutableLiveDataState.emit(HomeViewState.Failure(error.errorMessage))
    }
}