package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper

import com.rafaellsdev.cryptocurrencyprices.commons.ext.doubleOrZero
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.CurrencyResponse

fun List<CurrencyResponse>.toCurrencyList() =
    this.map {
        Currency(
            id = it.id.orEmpty(),
            symbol = it.symbol.orEmpty(),
            name = it.name.orEmpty(),
            image = it.image.orEmpty(),
            marketCap = it.marketCap.doubleOrZero(),
            currentPrice = it.currentPrice.doubleOrZero(),
            priceChangePercentage = it.priceChangePercentage.doubleOrZero(),
            highPrice = it.highPrice.doubleOrZero(),
            lowPrice = it.lowPrice.doubleOrZero(),
            totalVolume = it.totalVolume.doubleOrZero(),
            circulatingSupply = it.circulatingSupply.doubleOrZero(),
            totalSupply = it.totalSupply.doubleOrZero()
        )
    }