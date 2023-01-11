package com.example.bookUser.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookUser.data.Repository
import com.example.bookUser.data.ResultBooks
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository): ViewModel() {

     val list: LiveData<List<ResultBooks>> = repository.list

     fun getBook() {
          viewModelScope.launch {
               repository.getBook()
          }

     }

     fun searchBook(title: String){
          viewModelScope.launch {
               repository.getSearchBook(title)
          }
     }


}