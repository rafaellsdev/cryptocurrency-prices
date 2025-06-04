package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper

import com.rafaellsdev.cryptocurrencyprices.commons.model.Category
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.CategoryResponse

fun List<CategoryResponse>.toCategoryList() =
    this.map {
        Category(
            id = it.id.orEmpty(),
            name = it.name.orEmpty()
        )
    }
