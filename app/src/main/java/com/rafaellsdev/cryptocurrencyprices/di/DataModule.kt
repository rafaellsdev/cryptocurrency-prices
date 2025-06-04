package com.rafaellsdev.cryptocurrencyprices.di

import com.rafaellsdev.cryptocurrencyprices.commons.const.URLs.BASE_URL
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepositoryImp
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.DiscoverService
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.ConverterService
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.converter.CurrencyConverterRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.converter.CurrencyConverterRepositoryImp
import com.rafaellsdev.cryptocurrencyprices.commons.currency.CurrencyPreferenceRepository
import com.rafaellsdev.cryptocurrencyprices.commons.currency.CurrencyPreferenceRepositoryImp
import com.rafaellsdev.cryptocurrencyprices.commons.favorites.FavoritesRepository
import com.rafaellsdev.cryptocurrencyprices.commons.favorites.FavoritesRepositoryImp
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
    fun provideConverterService(retrofit: Retrofit): ConverterService =
        retrofit.create(ConverterService::class.java)

    @Singleton
    @Provides
    fun provideCurrencyPreferenceRepository(preferences: SharedPreferences): CurrencyPreferenceRepository =
        CurrencyPreferenceRepositoryImp(preferences)

    @Singleton
    @Provides
    fun provideDiscoverRepository(
        discoverService: DiscoverService,
        currencyPreferenceRepository: CurrencyPreferenceRepository
    ): CurrencyRepository =
        CurrencyRepositoryImp(discoverService, currencyPreferenceRepository)

    @Singleton
    @Provides
    fun provideCurrencyConverterRepository(
        converterService: ConverterService
    ): CurrencyConverterRepository =
        CurrencyConverterRepositoryImp(converterService)

    @Singleton
    @Provides
    fun provideFavoritesRepository(preferences: SharedPreferences): FavoritesRepository =
        FavoritesRepositoryImp(preferences)
}