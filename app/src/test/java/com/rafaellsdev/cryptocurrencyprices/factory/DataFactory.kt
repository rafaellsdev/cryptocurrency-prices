package com.rafaellsdev.cryptocurrencyprices.factory

import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.commons.model.TrendingCoin

fun currencyList() =
    listOf(
        Currency(
            id = "bitcoin",
            symbol = "btc",
            name = "Bitcoin",
            image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
            marketCap = 1000000.0,
            currentPrice = 42938.0,
            priceChangePercentage = -2.56888,
            highPrice = 44444.0,
            lowPrice = 42008.0,
            totalVolume = 5000000.0,
            circulatingSupply = 18000000.0,
            totalSupply = 21000000.0
        )
    )

fun trendingList() =
    listOf(
        TrendingCoin(
            id = "bitcoin",
            symbol = "btc",
            name = "Bitcoin",
            image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579"
        )
    )