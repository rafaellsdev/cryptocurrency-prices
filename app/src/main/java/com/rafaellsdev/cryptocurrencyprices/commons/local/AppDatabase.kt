package com.rafaellsdev.cryptocurrencyprices.commons.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyEntity::class, TrendingCoinEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
    abstract fun trendingCoinDao(): TrendingCoinDao
}
