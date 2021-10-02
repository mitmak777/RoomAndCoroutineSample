package com.example.myapplication.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface CurrencyDAO {

    @Query("SELECT * FROM currency")
    fun getAll(): Flow<List<Currency>>

    @Query("SELECT * FROM currency ORDER BY " +
            "        CASE WHEN :asc = 1 THEN name END ASC, " +
            "        CASE WHEN :asc = 0 THEN name END DESC")
    fun getAll(asc: Boolean): Flow<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(currenct: Currency)

    @Query("DELETE FROM currency")
    suspend fun deleteAll()
}

