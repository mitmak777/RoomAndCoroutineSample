package com.example.myapplication.ui.main

import androidx.lifecycle.*
import com.example.myapplication.data.Currency
import com.example.myapplication.data.CurrencyRepository

class MainViewModel(private val repository: CurrencyRepository) : ViewModel() {
    // TODO: Implement the ViewModel

    private var cryptoList: LiveData<List<Currency>> = repository.allCurrency.asLiveData()


    fun getCryptoList() = cryptoList
}

class MainViewModelFactory(private val repository: CurrencyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
