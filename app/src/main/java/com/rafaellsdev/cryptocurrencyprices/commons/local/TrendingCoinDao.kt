package com.rafaellsdev.cryptocurrencyprices.commons.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TrendingCoinDao {
    @Query("SELECT * FROM trending_coins")
    suspend fun getTrendingCoins(): List<TrendingCoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingCoins(coins: List<TrendingCoinEntity>)
}
