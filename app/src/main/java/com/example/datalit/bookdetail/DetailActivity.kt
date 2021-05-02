package com.example.datalit.bookdetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.datalit.R
import com.example.datalit.SQlite.DatabaseBook
import com.example.datalit.favorite.FavoriteViewModel
import com.example.datalit.model.BookItem
import kotlinx.android.synthetic.main.detail_activity.*
import java.io.*


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

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

        val aBar = supportActionBar
        aBar?.title = Titl


        if (Desc != null) {
            tvDesc.text = "Описание: " + Desc
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
            tvJanr.text =
                "Жанр: " + Catg.joinToString(separator = ", ")
        } else {
            tvJanr.text = "Жанр отсутствует"
        }
        val url = bookItem?.volumeInfo?.imageLinks?.thumbnail?.replace("http:", "https:")
        if (Publ != null) {
            tvPubl.text = Publ
        } else {
            tvPubl.text = "Издательство отсутствует"
        }
        if (Page != null) {
            tvCount.text = "Количество страниц: " + Page.toString()
        } else {
            tvCount.text = "Количество страниц: --"
        }
        if (Date != null) {
            tvDate.text = "Дата издания: " + Date
        } else {
            tvDate.text = "Дата отсутствует"
        }

        Glide.with(bog_book_img.context)
            .load(url)
            .placeholder(R.drawable.ic_book)
            .into(bog_book_img);
// Кнопки
        gettoweb.setOnClickListener {
            bookItem?.volumeInfo?.previewLink?.let { it1 -> openNewTabWindow(it1, this) }
        }
        download.setOnClickListener {
            saveTextFile(Titl.toString(), Desc.toString(), Titl?.replace(" ", "").toString())
        }

        //        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

    }

    fun delFromList() {
        val intent: Intent = getIntent()
        val bookItem = intent.getParcelableExtra<BookItem>("book")

        val Titl = bookItem?.volumeInfo?.title

        FavoriteViewModel(application).delBook(Titl)
    }

    fun addToBase(text: String) {

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

        val fileName = "DL" + name
        val file: File = File(fileName)
        val intent: Intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TITLE, fileName)
        startActivityForResult(intent, 4711)


        // intent.data?.let { writeInFile(it, descrip) }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val intent: Intent = getIntent()
        val bookItem = intent.getParcelableExtra<BookItem>("book")
        val Descript = bookItem?.volumeInfo?.description.toString()
        if (requestCode == 4711) {
            when (resultCode) {
                RESULT_OK -> if (data != null
                    && data.data != null
                ) {
                    writeInFile(data.data!!, Descript)
                }
                RESULT_CANCELED -> {
                }
            }
        }
    }

    private fun writeInFile(uri: Uri, text: String) {
        val outputStream: OutputStream?
        try {
            outputStream = contentResolver.openOutputStream(uri)
            val bw = BufferedWriter(OutputStreamWriter(outputStream))
            bw.write(text)
            bw.flush()
            bw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.addtofav -> {


                if (item.title == "Избрать") {
                    addToBase("Уже есть в списке")
                    item.title = "Добавленно в избранное"
                    item.setIcon(R.drawable.ic_favorite_epm)
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                } else {
                    delFromList()
                    item.title = "Избрать"
                    item.setIcon(R.drawable.ic_favorite_tool)
                }
            }


        }
        return super.onOptionsItemSelected(item)
    }

}


