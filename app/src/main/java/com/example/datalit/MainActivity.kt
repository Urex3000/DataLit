package com.example.datalit

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datalit.about.AboutActivity
import com.example.datalit.adapter.MainAdapter
import com.example.datalit.favorite.FavoriteActivity
import kotlinx.android.synthetic.main.activity_about.view.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    var mainAdapter: MainAdapter? = null
    val myViewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        initRecycler()
        observeData()
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_bar, menu)
        val mSearchBar = menu?.findItem(R.id.bar_search)
        val mSearchView = mSearchBar?.actionView as SearchView
        mSearchView.queryHint = "Искать..."
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (mSearchView.isNotEmpty()) {
                    myViewModel.loadData(p0.toString())
                } else {
                    Toast.makeText(this@MainActivity, "Пустой запрос", Toast.LENGTH_LONG).show()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {

            R.id.btAbout -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }

            R.id.favlist -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }

        }
        return super.onOptionsItemSelected(item)
    }

}


