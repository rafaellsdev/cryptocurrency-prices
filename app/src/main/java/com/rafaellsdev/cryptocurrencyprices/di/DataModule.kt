package com.rafaellsdev.cryptocurrencyprices.di

import com.rafaellsdev.cryptocurrencyprices.commons.const.URLs.BASE_URL
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepositoryImp
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.DiscoverService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideDiscoverService(retrofit: Retrofit): DiscoverService =
        retrofit.create(DiscoverService::class.java)

    @Singleton
    @Provides
    fun provideDiscoverRepository(discoverService: DiscoverService): CurrencyRepository =
        CurrencyRepositoryImp(discoverService)
}