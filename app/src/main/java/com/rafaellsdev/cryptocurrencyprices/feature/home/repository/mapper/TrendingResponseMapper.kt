package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper

import com.rafaellsdev.cryptocurrencyprices.commons.model.TrendingCoin
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.TrendingResponse

fun TrendingResponse.toTrendingCoinList(): List<TrendingCoin> =
    this.coins?.mapNotNull { container ->
        container.item?.let {
            TrendingCoin(
                id = it.id.orEmpty(),
                symbol = it.symbol.orEmpty(),
                name = it.name.orEmpty(),
                image = it.image.orEmpty()
            )
        }
    } ?: emptyList()
