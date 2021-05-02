package com.example.datalit.SQlite

import androidx.lifecycle.LiveData

class FavoriteRepository(private val daoClass: DaoClass) {

    val readAllData: LiveData<List<DatabaseBook>> = daoClass.getAll()

    fun addBookToFavorite(bookFav: DatabaseBook) {
        daoClass.insert(bookFav)
    }

    fun deleteFromFav(title: String?) {
        daoClass.delete(title)
    }

    fun deleteFavList() {
        daoClass.deleteAll()
    }
}