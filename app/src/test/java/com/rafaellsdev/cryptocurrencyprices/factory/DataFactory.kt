package com.rafaellsdev.cryptocurrencyprices.factory

import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency

fun currencyList() =
    listOf(
        Currency(
            id = "bitcoin",
            symbol = "btc",
            name = "Bitcoin",
            image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
            currentPrice = 42938.0,
            priceChangePercentage = -2.56888,
            highPrice = 44444.0,
            lowPrice = 42008.0
        )
    )