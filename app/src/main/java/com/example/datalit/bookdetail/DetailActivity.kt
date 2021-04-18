package com.example.datalit.bookdetail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.datalit.R
import com.example.datalit.SQlite.DatabaseBook
import com.example.datalit.favorite.FavoriteViewModel
import com.example.datalit.model.BookItem
import kotlinx.android.synthetic.main.detail_fragment.*
import java.io.File


class DetailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_fragment)


        val intent: Intent = getIntent()
        val bookItem = intent.getParcelableExtra<BookItem>("book")

        //Обозначение
        val Titl = bookItem?.volumeInfo?.title
        val Desc = bookItem?.volumeInfo?.description
        val Auth = bookItem?.volumeInfo?.authors
        val Catg = bookItem?.volumeInfo?.categories
        val Publ = bookItem?.volumeInfo?.publisher
        val Page = bookItem?.volumeInfo?.pageCount
        val Date = bookItem?.volumeInfo?.publishedDate

        tvTitle.text = Titl
        if (Desc != null) {
            tvDesc.text = Desc
        } else {
            tvDesc.text =
                "Описание отсутствует"
        }

        if (Auth != null) {
            tvAuthor.text = "Автор: " + Auth.joinToString(separator = ", ")
        } else {
            tvAuthor.text = "Автор отсутствует"
        }

        if (Catg != null) {
            tvCategory.text =
                "Жанр: " + Catg.joinToString(separator = ", ")
        } else {
            tvCategory.text = "Жанр отсутствует"
        }
        val url = bookItem?.volumeInfo?.imageLinks?.thumbnail?.replace("http:", "https:")
        if (Publ != null) {
            tvPublisher.text = Publ
        } else {
            tvPublisher.text = "Издательство отсутствует"
        }
        if (Page != null) {
            tvPage.text = "Количество страниц: " + Page.toString()
        } else {
            tvPage.text = "Количество страниц: --"
        }
        if (Date != null) {
            tvDate.text = "Дата издания: " + Date
        } else {
            tvDate.text = "Дата отсутствует"
        }

        Glide.with(ivBigBook.context)
            .load(url)
            .placeholder(R.drawable.ic_book)
            .into(ivBigBook);
// Кнопки
        btnGet.setOnClickListener {
            bookItem?.volumeInfo?.previewLink?.let { it1 -> openNewTabWindow(it1, this) }
        }
        btnSave.setOnClickListener {
            saveTextFile(Titl.toString(), Desc.toString(), Titl?.replace(" ", "").toString())
        }

//        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        btnFav.setOnClickListener {

            addToBase()
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()


        }
    }

    fun addToBase() {

        val intent: Intent = getIntent()
        val bookItem = intent.getParcelableExtra<BookItem>("book")

        val Titl = bookItem?.volumeInfo?.title
        val Desc = bookItem?.volumeInfo?.description
        val Auth = bookItem?.volumeInfo?.authors?.firstOrNull()
        val Catg = bookItem?.volumeInfo?.categories?.firstOrNull()
        val Publ = bookItem?.volumeInfo?.publisher
        val Page = bookItem?.volumeInfo?.pageCount
        val Date = bookItem?.volumeInfo?.publishedDate
        val Thumbnail = bookItem?.volumeInfo?.imageLinks?.thumbnail?.replace("http:", "https:")
        val Smallthumbnail =
            bookItem?.volumeInfo?.imageLinks?.smallThumbnail?.replace("http:", "https:")
        val Link = bookItem?.volumeInfo?.previewLink
        val data: DatabaseBook = DatabaseBook(
            null, Auth, Catg, Desc, Smallthumbnail, Thumbnail, Page, Link, Date, Publ, Titl
        )
        FavoriteViewModel(application).addBook(data)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_option_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


    fun openNewTabWindow(urls: String, context: Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        context.startActivity(intents)
    }

    //    Доделать
    fun saveTextFile(title: String, descrip: String, name: String) {

        val fileName = "DL" + name + ".txt"
        val file: File = File(fileName)
        val intent: Intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TITLE, fileName)
        startActivity(intent)

    }


}


