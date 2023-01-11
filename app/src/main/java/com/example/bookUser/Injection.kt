package com.example.bookUser

import android.content.Context
import com.example.bookUser.Api.ApiInstance
import com.example.bookUser.data.Repository


object Injection {

    fun provideRepository(context: Context): Repository {
        val apiService = ApiInstance.getApiService()


        return Repository.getInstance(apiService)
    }
}