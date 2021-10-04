package com.example.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.data.Currency
import com.example.myapplication.data.CurrencyRepository
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


class MainViewModel(private val repository: CurrencyRepository) : ViewModel() {

    private var isAsc = false

    private var cryptoList: MutableLiveData<List<Currency>> = MutableLiveData(emptyList())

    private var currentJob : Job? = null

    val mutex = Mutex()

    fun getCryptoList() : LiveData<List<Currency>>{

        return cryptoList
    }


    fun updateSort(){
        //sort the list at non-UI thread
        if(currentJob?.isActive == true)
            currentJob?.cancel()
        currentJob = viewModelScope.launch(Dispatchers.IO){
            //use mutex to ensure only 1 thread update the list
            mutex.withLock {
                sortList()
            }
        }
    }



    fun loadList(){
        viewModelScope.launch {
            // the query will run at non-ui thread and collect at ui thread for update
            repository.allCurrency.collect{
                cryptoList.postValue(it)
            }

        }

    }







    private suspend fun sortList(){
        val list = cryptoList.value
        list?.let{
            isAsc = !isAsc
            yield() // check if the current job is active, if not don't post value
            cryptoList.postValue(when(isAsc) {
                true ->list.sortedBy { it.name }
                false-> list.sortedByDescending { it.name }
            })

        }


    }


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
