package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper

import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.CurrencyResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class DiscoverResponseMapperTest {

    @Test
    fun `test toCurrencyList with valid data`() {
        val currencyResponseList = listOf(
            CurrencyResponse(
                id = "1",
                symbol = "BTC",
                name = "Bitcoin",
                image = "image_url",
                currentPrice = 50000.0,
                priceChangePercentage = 5.0,
                highPrice = 55000.0,
                lowPrice = 45000.0
            )
        )

        val currencyList = currencyResponseList.toCurrencyList()

        val expectedCurrencyList = listOf(
            Currency(
                id = "1",
                symbol = "BTC",
                name = "Bitcoin",
                image = "image_url",
                currentPrice = 50000.0,
                priceChangePercentage = 5.0,
                highPrice = 55000.0,
                lowPrice = 45000.0
            )
        )

        assertEquals(expectedCurrencyList, currencyList)
    }

    @Test
    fun `test toCurrencyList with null values`() {
        val currencyResponseList = listOf(
            CurrencyResponse(
                id = null,
                symbol = null,
                name = null,
                image = null,
                currentPrice = null,
                priceChangePercentage = null,
                highPrice = null,
                lowPrice = null
            )
        )

        val currencyList = currencyResponseList.toCurrencyList()

        val expectedCurrencyList = listOf(
            Currency(
                id = "",
                symbol = "",
                name = "",
                image = "",
                currentPrice = 0.0,
                priceChangePercentage = 0.0,
                highPrice = 0.0,
                lowPrice = 0.0
            )
        )

        assertEquals(expectedCurrencyList, currencyList)
    }
}