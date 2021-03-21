package com.example.datalit.favorite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datalit.R
import com.example.datalit.adapter.MainAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    val viewModel: FavoriteViewModel by viewModel()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fav_activity)
        println("AAA activity")
        val recyclerView = findViewById<RecyclerView>(R.id.rvFavorite)

        val supportActionBar = supportActionBar
        supportActionBar!!.title = "Избранное"
        supportActionBar.setDisplayHomeAsUpEnabled(true)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        observeData()

    }

    private fun observeData() {


        viewModel.getAllData.observe(this, { list ->

            if (list != null && list.isNotEmpty()) {
                val listBookItems = list.map { it.toBookItem() }
                adapter.replaceAll(listBookItems)
                listBookItems.toSet()
            } else {
                Toast.makeText(this, "Empty list", Toast.LENGTH_LONG).show()
            }


        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}