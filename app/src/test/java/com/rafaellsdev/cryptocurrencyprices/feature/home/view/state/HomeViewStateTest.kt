package com.rafaellsdev.cryptocurrencyprices.feature.home.view.state

import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeViewStateTest {

    @Test
    fun testLoadingState() {
        val state = HomeViewState.Loading
        assertEquals(HomeViewState.Loading, state)
    }

    @Test
    fun testSuccessState() {
        val currencies = listOf(
            Currency(
                id = "1",
                symbol = "BTC",
                name = "Bitcoin",
                image = "https://example.com/bitcoin.png",
                currentPrice = 50000.0,
                priceChangePercentage = 5.0,
                highPrice = 52000.0,
                lowPrice = 48000.0
            )
        )
        val state = HomeViewState.Success(currencies)
        assertEquals(currencies, state.currencies)
    }

    @Test
    fun testFailureState() {
        val errorMessage = "Error loading data"
        val state = HomeViewState.Failure(errorMessage)
        assertEquals(errorMessage, state.errorMessage)
    }
}