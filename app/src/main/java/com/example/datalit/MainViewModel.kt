package com.example.datalit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datalit.model.BookItem
import com.example.datalit.model.BookList
import com.example.datalit.repo.BooksRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(private val repo: BooksRepo) : ViewModel() {

    private val liveData: MutableLiveData<Result> by lazy {
        MutableLiveData<Result>()
    }

    init {
        loadData("СССР")
    }


    fun loadData(query: String) {

        val call = repo.getData(query)
        call.enqueue(object : Callback<BookList> {
            override fun onResponse(call: Call<BookList>, response: Response<BookList>) {
                val items = response.body()?.items
                if (items != null) {
                    val result: Result = Result(items, "")
                    liveData.postValue(result);
//                    mainAdapter?.addAll(items)
                } else {
                    val result: Result = Result(null, "Данных больше нет!")
                    liveData.postValue(result);
                }
            }


            override fun onFailure(call: Call<BookList>, t: Throwable) {
                val result: Result = Result(null, "Ошибка ${t.message}")
                Log.e("MainViewModel", t.localizedMessage)
                liveData.postValue(result);
            }

        })

    }

    fun getData(): LiveData<Result> {
        return liveData
    }

    data class Result(val bookList: List<BookItem>?, val error: String)
}