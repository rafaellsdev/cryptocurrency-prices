package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper

import com.rafaellsdev.cryptocurrencyprices.commons.ext.doubleOrZero
import com.rafaellsdev.cryptocurrencyprices.commons.model.CoinCategory
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.CategoryResponse

fun List<CategoryResponse>.toCategoryList(): List<CoinCategory> =
    this.map {
        CoinCategory(
            id = it.id.orEmpty(),
            name = it.name.orEmpty(),
            marketCap = it.marketCap.doubleOrZero()
        )
    }
