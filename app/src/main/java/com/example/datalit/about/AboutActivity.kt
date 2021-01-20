package com.example.datalit.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.datalit.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val supportActionBar = supportActionBar
        supportActionBar!!.title = "О нас"
        supportActionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}