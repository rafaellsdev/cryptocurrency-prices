package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper

import com.rafaellsdev.cryptocurrencyprices.commons.ext.orZero
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.CurrencyResponse

fun List<CurrencyResponse>.toCurrencyList(): List<Currency> {
    val currencyList = mutableListOf<Currency>()

    this.forEach {
        currencyList.add(
            Currency(
                id = it.id.orEmpty(),
                symbol = it.symbol.orEmpty(),
                name = it.name.orEmpty(),
                image = it.image.orEmpty(),
                currentPrice = it.currentPrice.orZero(),
                priceChangePercentage = it.priceChangePercentage.orZero(),

            )
        )
    }

    return currencyList
}