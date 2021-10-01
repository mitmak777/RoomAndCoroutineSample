package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.CurrencyDatabase
import com.example.myapplication.data.CurrencyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { CurrencyDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { CurrencyRepository(database.currencyDao()) }
}