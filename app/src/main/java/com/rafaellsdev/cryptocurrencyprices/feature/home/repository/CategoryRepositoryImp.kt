package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.CoinCategory
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper.toCategoryList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.CategoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepositoryImp @Inject constructor(
    private val service: CategoryService
) : CategoryRepository {
    override suspend fun getCategories(): List<CoinCategory> =
        withContext(Dispatchers.IO) {
            service.getCategories().toCategoryList()
        }
}
