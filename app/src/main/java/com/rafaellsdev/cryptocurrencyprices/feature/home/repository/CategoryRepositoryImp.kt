package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.commons.model.CoinCategory
import com.rafaellsdev.cryptocurrencyprices.commons.local.CategoryDao
import com.rafaellsdev.cryptocurrencyprices.commons.local.toDomain
import com.rafaellsdev.cryptocurrencyprices.commons.local.toEntity
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper.toCategoryList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.CategoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryRepositoryImp @Inject constructor(
    private val service: CategoryService,
    private val categoryDao: CategoryDao
) : CategoryRepository {
    override suspend fun getCategories(): List<CoinCategory> =
        withContext(Dispatchers.IO) {
            try {
                val result = service.getCategories().toCategoryList()
                categoryDao.insertCategories(result.map { it.toEntity() })
                result
            } catch (e: Exception) {
                if (e is IOException || (e is HttpException && e.code() == 429)) {
                    categoryDao.getCategories().map { it.toDomain() }
                } else {
                    throw e
                }
            }
        }
}
