package com.example.myapplication.ui.main

import androidx.lifecycle.*
import com.example.myapplication.data.Currency
import com.example.myapplication.data.CurrencyRepository
import androidx.lifecycle.MutableLiveData




class MainViewModel(private val repository: CurrencyRepository) : ViewModel() {

    private val loadState : MutableLiveData<LoadState> = MutableLiveData(LoadState.NONE)
    private var isAsc = false

    private var cryptoList: LiveData<List<Currency>> =
        Transformations.switchMap(loadState) { state ->
            return@switchMap when (state) {
                LoadState.NONE,null -> MutableLiveData(emptyList())
                LoadState.LOAD -> repository.allCurrency.asLiveData()
                LoadState.SORT_ASC -> repository.getSortedCurrency(true).asLiveData()
                LoadState.SORT_DES -> repository.getSortedCurrency(false).asLiveData()
            }


        }


    fun getCryptoList() : LiveData<List<Currency>>{

        return cryptoList
    }

    fun updateSort(){
        if(loadState.value == LoadState.NONE)
            return
        isAsc = !isAsc
        loadState.postValue(when (isAsc){ true -> LoadState.SORT_ASC false-> LoadState.SORT_DES})
    }

    fun loadList(){
        loadState.postValue(LoadState.LOAD)

    }


}

enum class LoadState {
    NONE,
    LOAD,
    SORT_ASC,
    SORT_DES

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
