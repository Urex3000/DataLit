package com.example.datalit.bookdetail

import android.app.Activity
import android.content.Intent
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


        tvTitle.text = bookItem?.volumeInfo?.title

    }

}



