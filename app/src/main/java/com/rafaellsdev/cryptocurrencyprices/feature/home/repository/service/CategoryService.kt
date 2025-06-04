package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service

import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.CategoryResponse
import retrofit2.http.GET

interface CategoryService {
    @GET("coins/categories")
    suspend fun getCategories(): List<CategoryResponse>
}
