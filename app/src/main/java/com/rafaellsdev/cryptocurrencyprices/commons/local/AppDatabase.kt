package com.rafaellsdev.cryptocurrencyprices.commons.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CurrencyEntity::class, TrendingCoinEntity::class, CategoryEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
    abstract fun trendingCoinDao(): TrendingCoinDao
    abstract fun categoryDao(): CategoryDao
}
