package com.example.datalit.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.datalit.R

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fav_activity)
    }
}