package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper

import com.rafaellsdev.cryptocurrencyprices.commons.model.SearchCoin
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.SearchResponse

fun SearchResponse.toSearchCoinList(): List<SearchCoin> =
    this.coins?.map {
        SearchCoin(
            id = it.id.orEmpty(),
            symbol = it.symbol.orEmpty(),
            name = it.name.orEmpty(),
            image = it.image.orEmpty()
        )
    } ?: emptyList()
