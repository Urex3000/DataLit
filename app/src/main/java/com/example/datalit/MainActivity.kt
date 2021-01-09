package com.example.datalit

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datalit.adapter.MainAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    var mainAdapter: MainAdapter? = null
    val myViewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        println("AAAAAA onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchBox = findViewById<EditText>(R.id.etSearchText)
        val searchButton = findViewById<ImageButton>(R.id.btnSearch)

        searchButton.setOnClickListener {
            if (searchBox.text.isNotBlank()) {
                myViewModel.loadData(searchBox.text.toString())
            } else {
                Toast.makeText(this@MainActivity, "Пустой запрос", Toast.LENGTH_LONG).show()
            }
        }

        initRecycler()
        observeData()
    }


    override fun onStart() {
        println("AAAAAA onStart")
        super.onStart()
    }


    override fun onResume() {
        println("AAAAAA onResume")
        super.onResume()
    }

    override fun onPause() {
        println("AAAAAA onPause")
        super.onPause()
    }

    override fun onStop() {
        println("AAAAAA onStop")
        super.onStop()
    }

    override fun onDestroy() {
        println("AAAAAA onDestroy")
        super.onDestroy()
    }

    private fun initRecycler() {
        val recyclerView: RecyclerView = findViewById(R.id.rv_books)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            mainAdapter = MainAdapter()
            adapter = mainAdapter
        }
    }

    private fun observeData() {


        myViewModel.getData().observe(this, { result ->
            if (result.bookList != null) {
                mainAdapter?.replaceAll(result.bookList)
            } else {
                Toast.makeText(this@MainActivity, result.error, Toast.LENGTH_LONG).show()
            }


        })
    }


}


