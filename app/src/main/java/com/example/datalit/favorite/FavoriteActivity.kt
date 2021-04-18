package com.example.datalit.favorite

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delAll -> viewModel.delFavList()
        }
        return super.onOptionsItemSelected(item)
    }

/*fun alert(s1: String) {


    val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> Toast.makeText(
                ,
                "Хорошо",
                Toast.LENGTH_SHORT
            ).show()
            DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(
                itemView.context,
                "Отмена",
                Toast.LENGTH_SHORT
            ).show()
        }
        val alertDialog = androidx.appcompat.app.AlertDialog.Builder(itemView.context)
            .setTitle("Убрать книгу из Избранного?")
            .setPositiveButton("Да", dialogClickListener)
            .setNegativeButton("Отмена", dialogClickListener)
            .create().show()

        true
    }
}*/

    private fun observeData() {


        viewModel.getAllData.observe(this, { list ->

            if (list != null && list.isNotEmpty()) {
                val listBookItems = list.map { it.toBookItem() }
                adapter.replaceAll(listBookItems)
                listBookItems.toSet()
            } else {
                Toast.makeText(this, "Избранных нет", Toast.LENGTH_LONG).show()
            }


        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}