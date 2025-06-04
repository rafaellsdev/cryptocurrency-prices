package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.Category
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper.toCategoryList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.DiscoverService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepositoryImp @Inject constructor(
    private val discoverService: DiscoverService
) : CategoryRepository {
    override suspend fun getCategories(): List<Category> = withContext(Dispatchers.IO) {
        discoverService.getCategories().toCategoryList()
    }
}
