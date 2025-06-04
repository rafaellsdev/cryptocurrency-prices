package com.rafaellsdev.cryptocurrencyprices.di

import com.rafaellsdev.cryptocurrencyprices.commons.const.URLs.BASE_URL
import com.rafaellsdev.cryptocurrencyprices.commons.local.AppDatabase
import com.rafaellsdev.cryptocurrencyprices.commons.local.CurrencyDao
import com.rafaellsdev.cryptocurrencyprices.commons.local.TrendingCoinDao
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepositoryImp
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.TrendingRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.TrendingRepositoryImp
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CategoryRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CategoryRepositoryImp
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.DiscoverService
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.TrendingService
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.CategoryService
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.NewsRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.NewsRepositoryImp
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.NewsService
import com.rafaellsdev.cryptocurrencyprices.commons.const.URLs.NEWS_BASE_URL
import com.rafaellsdev.cryptocurrencyprices.commons.currency.CurrencyPreferenceRepository
import com.rafaellsdev.cryptocurrencyprices.commons.currency.CurrencyPreferenceRepositoryImp
import com.rafaellsdev.cryptocurrencyprices.commons.favorites.FavoritesRepository
import com.rafaellsdev.cryptocurrencyprices.commons.favorites.FavoritesRepositoryImp
import android.content.SharedPreferences
import com.rafaellsdev.cryptocurrencyprices.BaseApplication
import androidx.room.Room
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
    fun provideDatabase(app: BaseApplication): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "prices.db").build()

    @Singleton
    @Provides
    fun provideCurrencyDao(database: AppDatabase): CurrencyDao =
        database.currencyDao()

    @Singleton
    @Provides
    fun provideTrendingCoinDao(database: AppDatabase): TrendingCoinDao =
        database.trendingCoinDao()

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
    fun provideTrendingService(retrofit: Retrofit): TrendingService =
        retrofit.create(TrendingService::class.java)

    @Singleton
    @Provides
    fun provideCategoryService(retrofit: Retrofit): CategoryService =
        retrofit.create(CategoryService::class.java)

    @Singleton
    @Provides
    fun provideCurrencyPreferenceRepository(preferences: SharedPreferences): CurrencyPreferenceRepository =
        CurrencyPreferenceRepositoryImp(preferences)

    @Singleton
    @Provides
    fun provideDiscoverRepository(
        discoverService: DiscoverService,
        currencyPreferenceRepository: CurrencyPreferenceRepository,
        currencyDao: CurrencyDao
    ): CurrencyRepository =
        CurrencyRepositoryImp(discoverService, currencyPreferenceRepository, currencyDao)

    @Singleton
    @Provides
    fun provideCategoryRepository(categoryService: CategoryService): CategoryRepository =
        CategoryRepositoryImp(categoryService)

    @Singleton
    @Provides
    fun provideTrendingRepository(
        trendingService: TrendingService,
        trendingCoinDao: TrendingCoinDao
    ): TrendingRepository =
        TrendingRepositoryImp(trendingService, trendingCoinDao)

    @Singleton
    @Provides
    fun provideFavoritesRepository(preferences: SharedPreferences): FavoritesRepository =
        FavoritesRepositoryImp(preferences)

    @Singleton
    @Provides
    fun provideNewsService(): NewsService =
        Retrofit.Builder()
            .baseUrl(NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)

    @Singleton
    @Provides
    fun provideNewsRepository(newsService: NewsService): NewsRepository =
        NewsRepositoryImp(newsService)
}