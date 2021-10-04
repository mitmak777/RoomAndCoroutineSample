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

@Database(entities = arrayOf(Currency::class), version = 1)
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


            currencyDao.insert(Currency("BTC", "Bitcoin", "BTC"))
            currencyDao.insert(Currency("ETH", "Ethereum", "ETH"))
            currencyDao.insert(Currency("XRP", "XRP", "XRP"))
            currencyDao.insert(Currency("BCH", "Bitcoin Cash", "BCH"))
            currencyDao.insert(Currency("LTC", "Litecoin", "LTC"))
            currencyDao.insert(Currency("EOS", "EOS", "EOS"))
            currencyDao.insert(Currency("BNB", "Binance Coin", "BNB"))
            currencyDao.insert(Currency("LINK", "ChainLink", "LINK"))
            currencyDao.insert(Currency("NEO", "NEO", "NEO"))
            currencyDao.insert(Currency("ETC", "Ethereum Classic", "ETC"))
            currencyDao.insert(Currency("ONT", "Ontology", "ONT"))
            currencyDao.insert(Currency("CRO", "Crypto.com Chain", "CRO"))
            currencyDao.insert(Currency("CUC", "Cucumber", "CUC"))
            currencyDao.insert(Currency("USDC", "USD Coin", "USDC"))

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