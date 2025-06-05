package com.rafaellsdev.cryptocurrencyprices.commons.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    suspend fun getCurrencies(): List<CurrencyEntity>

    @Query("SELECT * FROM currencies WHERE id IN (:ids)")
    suspend fun getCurrenciesByIds(ids: List<String>): List<CurrencyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<CurrencyEntity>)
}
