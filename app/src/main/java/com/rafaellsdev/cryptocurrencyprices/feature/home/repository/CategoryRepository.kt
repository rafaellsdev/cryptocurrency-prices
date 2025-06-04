package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}
