package com.example.datalit.bookdetail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.datalit.R
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
                "Описания нееету(( я не виноват, честно. Посмотрите другие книги, там точно есть!"
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

        btnGet.setOnClickListener {
            bookItem?.volumeInfo?.previewLink?.let { it1 -> openNewTabWindow(it1, this) }
        }
        btnSave.setOnClickListener {
            saveTextFile(Titl.toString(), Desc.toString(), Titl?.replace(" ", "").toString())

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

    fun saveTextFile(title: String, descrip: String, name: String) {

        val fileName = "DL" + name + ".txt"
        val file: File = File(fileName)
        val intent: Intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TITLE, fileName)
        startActivity(intent)


        /*try {
            val fos : FileOutputStream= FileOutputStream(file)
            fos.write((title + "\n"+ descrip).toByteArray())
            fos.close()
        } catch (e: IOException){
            Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()
        }*/


        /* val fileOutputStream: FileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
         val outputWriter = OutputStreamWriter(fileOutputStream)
         outputWriter.write(title + "\n"+ descrip)
         outputWriter.close()*/

    }
    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
          super.onActivityResult(requestCode, resultCode, data)

          if (resultCode == 1){
              if (resultCode == RESULT_OK){
                  val uri: Uri? = data?.getData()
                  try {
                      val outputStream: OutputStream = getContentResolver().openOutputStream(uri!!)!!

                      outputStream.write("fffffffffffffffffffffffffffffffffff".toByteArray())
                      outputStream.close()
                      Toast.makeText(this,"Файл сохранен", Toast.LENGTH_SHORT).show()
                  } catch (e: IOException){
  Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                  }
              } else{
                  Toast.makeText(this,"Неудача", Toast.LENGTH_SHORT).show()
              }
          }
      }*/

}


//Uri uri = data.getData();

