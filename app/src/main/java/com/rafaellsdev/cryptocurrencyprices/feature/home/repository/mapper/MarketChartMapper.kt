package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper

import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.MarketChartResponse

fun MarketChartResponse.toPriceList(): List<Pair<Long, Double>> =
    prices.map { (time, price) -> time.toLong() to price }
