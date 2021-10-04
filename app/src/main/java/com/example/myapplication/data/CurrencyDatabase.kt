package com.example.myapplication.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(CurrencyInfo::class), version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDAO
    private class CurrencyDatabaseCallback(
        private val scope: CoroutineScope,
        private val requireInit : Boolean
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {

            super.onCreate(db)
            INSTANCE?.let { database ->
                if(requireInit)
                    scope.launch {
                        populateDatabase(database.currencyDao())
                    }
            }
        }
        //populate the currency table
        suspend fun populateDatabase(currencyDao: CurrencyDAO) {
            // Delete all old content (if any) here.
            currencyDao.deleteAll()


            currencyDao.insert(CurrencyInfo("BTC", "Bitcoin", "BTC"))
            currencyDao.insert(CurrencyInfo("ETH", "Ethereum", "ETH"))
            currencyDao.insert(CurrencyInfo("XRP", "XRP", "XRP"))
            currencyDao.insert(CurrencyInfo("BCH", "Bitcoin Cash", "BCH"))
            currencyDao.insert(CurrencyInfo("LTC", "Litecoin", "LTC"))
            currencyDao.insert(CurrencyInfo("EOS", "EOS", "EOS"))
            currencyDao.insert(CurrencyInfo("BNB", "Binance Coin", "BNB"))
            currencyDao.insert(CurrencyInfo("LINK", "ChainLink", "LINK"))
            currencyDao.insert(CurrencyInfo("NEO", "NEO", "NEO"))
            currencyDao.insert(CurrencyInfo("ETC", "Ethereum Classic", "ETC"))
            currencyDao.insert(CurrencyInfo("ONT", "Ontology", "ONT"))
            currencyDao.insert(CurrencyInfo("CRO", "Crypto.com Chain", "CRO"))
            currencyDao.insert(CurrencyInfo("CUC", "Cucumber", "CUC"))
            currencyDao.insert(CurrencyInfo("USDC", "USD Coin", "USDC"))

        }



    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CurrencyDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CurrencyDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            context.getSharedPreferences("setting",MODE_PRIVATE)

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyDatabase::class.java,
                    "currency_database"
                ).addCallback(CurrencyDatabaseCallback(scope,true))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}