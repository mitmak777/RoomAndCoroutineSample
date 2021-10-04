package com.example.myapplication.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface CurrencyDAO {

    @Query("SELECT * FROM currencyInfo")
    fun getAll(): Flow<List<CurrencyInfo>>

    @Query("SELECT * FROM currencyInfo ORDER BY " +
            "        CASE WHEN :asc = 1 THEN name END ASC, " +
            "        CASE WHEN :asc = 0 THEN name END DESC")
    fun getAll(asc: Boolean): Flow<List<CurrencyInfo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(currency: CurrencyInfo)

    @Query("DELETE FROM currencyInfo")
    suspend fun deleteAll()
}

