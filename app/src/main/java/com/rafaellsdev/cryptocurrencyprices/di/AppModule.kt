package com.rafaellsdev.cryptocurrencyprices.di

import android.content.Context
import com.rafaellsdev.cryptocurrencyprices.BaseApplication
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext context: Context): BaseApplication {
        return context as BaseApplication
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(app: BaseApplication): SharedPreferences {
        return app.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)
    }
}