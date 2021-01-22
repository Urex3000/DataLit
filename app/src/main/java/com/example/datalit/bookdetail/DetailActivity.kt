package com.example.datalit.bookdetail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.example.datalit.R
import com.example.datalit.model.BookItem
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_fragment)

        val intent: Intent = getIntent()
        val bookItem = intent.getParcelableExtra<BookItem>("book")

        /*val actionBar = action
        actionBar!!.title = "Подробности"
        actionBar.setDisplayHomeAsUpEnabled(true)*/


        tvTitle.text = bookItem?.volumeInfo?.title
        val Desc = bookItem?.volumeInfo?.description
        if (Desc != null) {
            tvDesc.text = Desc
        } else {
            tvDesc.text =
                "Описания нееету(( я не виноват, честно. Посмотрите другие книги, там точно есть!"
        }
        if (bookItem?.volumeInfo?.authors != null) {
            tvAuthor.text = "Автор: " + bookItem.volumeInfo.authors.joinToString(separator = ", ")
        } else {
            tvAuthor.text = "Автор отсутствует"
        }
        if (bookItem?.volumeInfo?.categories != null) {
            tvCategory.text =
                "Жанр: " + bookItem.volumeInfo.categories.joinToString(separator = ", ")
        } else {
            tvCategory.text = "Жанр отсутствует"
        }
//        val url = bookItem?.volumeInfo?.imageLinks?.thumbnail?.replace("http:", "https:")
        if (bookItem?.volumeInfo?.publisher != null) {
            tvPublisher.text = bookItem.volumeInfo.publisher
        } else {
            tvPublisher.text = "Издательство отсутствует"
        }
        if (bookItem?.volumeInfo?.pageCount != null) {
            tvPage.text = "Количество страниц: " + bookItem.volumeInfo.pageCount.toString()
        } else {
            tvPage.text = "Количество страниц: --"
        }
        if (bookItem?.volumeInfo?.publishedDate != null) {
            tvDate.text = "Дата издания: " + bookItem.volumeInfo.publishedDate
        } else {
            tvDate.text = "Дата отсутствует"
        }
        btnGet.setOnClickListener {
            bookItem?.volumeInfo?.previewLink?.let { it1 -> openNewTabWindow(it1, this) }
        }

    }

    /*   override fun onNavigateUp(): Boolean{
           onBackPressed()
           return true
       }*/
    fun openNewTabWindow(urls: String, context: Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        context.startActivity(intents)
    }
}




