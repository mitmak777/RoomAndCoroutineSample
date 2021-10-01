package com.example.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Crypto

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private var cryptoList: LiveData<List<Crypto>> = MutableLiveData<List<Crypto>>(
        listOf(
            Crypto("BTC", "Bitcoin", "BTC"),
            Crypto("BTC", "Bitcoin", "BTC"),
            Crypto("BTC", "Bitcoin", "BTC"),
            Crypto("BTC", "Bitcoin", "BTC"),
            Crypto("BTC", "Bitcoin", "BTC"),
            Crypto("BTC", "Bitcoin", "BTC"),
            Crypto("BTC", "Bitcoin", "BTC"),
            Crypto("BTC", "Bitcoin", "BTC"),
            Crypto("BTC", "Bitcoin", "BTC"),
            Crypto("BTC", "Bitcoin", "BTC"),

            Crypto("BTC", "Bitcoin", "BTC")))


    fun getCryptoList() = cryptoList
}
