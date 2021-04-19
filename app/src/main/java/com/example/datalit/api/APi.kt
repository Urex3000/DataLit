package com.example.datalit.api

import com.example.datalit.model.BookList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.googleapis.com/books/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BooksApiService {
    @GET("volumes")
    fun getProperties(
        @Query("q") query: String,
        @Query("key") api_key: String,
        @Query("maxResults") result_size: String = "40",
        @Query("orderBy") sorting: String,
        @Query("langRestrict") lang: String = "ru"
    ):
            Call<BookList>
}

object BooksApi {
    val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}