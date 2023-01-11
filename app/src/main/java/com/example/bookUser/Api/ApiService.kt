package com.example.bookUser.Api


import com.example.bookUser.data.ResultBooks
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("book")
     fun getBook(
     ):Call<List<ResultBooks>>

     @GET("book/{title}")
     fun searchBook(
         @Path("title") title: String?
     ):Call<List<ResultBooks>>



}