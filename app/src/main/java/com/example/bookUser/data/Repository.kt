package com.example.bookUser.data


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookUser.Api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository constructor(private val apiService: ApiService){

    private val _list = MutableLiveData<List<ResultBooks>>()
    val list: LiveData<List<ResultBooks>> = _list

    fun getBook() {
        val client = apiService.getBook()
        client.enqueue(object: Callback<List<ResultBooks>> {
            override fun onResponse(
                call: Call<List<ResultBooks>>,
                response: Response<List<ResultBooks>>
            ) {

                if (response.isSuccessful){
                    _list.value = response.body()

                }
                else{

                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ResultBooks>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
    fun getSearchBook(title: String){
        val client = apiService.searchBook(title)
        client.enqueue(object: Callback<List<ResultBooks>> {
            override fun onResponse(
                call: Call<List<ResultBooks>>,
                response: Response<List<ResultBooks>>
            ) {

                if (response.isSuccessful){
                    _list.value = response.body()

                }
                else{

                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ResultBooks>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService,

            ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService)
            }.also { instance = it }
    }

}