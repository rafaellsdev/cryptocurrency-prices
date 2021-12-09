package com.rafaellsdev.cryptocurrencyprices.di

import android.content.Context
import com.rafaellsdev.cryptocurrencyprices.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext context: Context): BaseApplication {
        return context as BaseApplication
    }
}