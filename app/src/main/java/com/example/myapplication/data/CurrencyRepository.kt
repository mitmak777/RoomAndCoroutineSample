package com.example.myapplication.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class CurrencyRepository(private val currencyDao: CurrencyDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allCurrency: Flow<List<CurrencyInfo>> = currencyDao.getAll()

    fun getSortedCurrency(isAsc : Boolean) = currencyDao.getAll(isAsc)

}