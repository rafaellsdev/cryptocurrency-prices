package com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service

import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("news/")
    suspend fun getNews(@Query("lang") lang: String = "EN"): NewsResponse
}
