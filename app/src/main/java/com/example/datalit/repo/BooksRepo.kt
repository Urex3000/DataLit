package com.example.datalit.repo

import com.example.datalit.api.BooksApi
import com.example.datalit.model.BookList
import retrofit2.Call

class BooksRepo() {
    companion object {
        private const val API_KEY = "AIzaSyCBMnwHbPJbs6AA3sJUO0XcLiwLn4aJXfE"
    }

    fun getData(query: String): Call<BookList> {
        val call = BooksApi.retrofitService.getProperties(
            query, API_KEY
        )
        return call
    }

}