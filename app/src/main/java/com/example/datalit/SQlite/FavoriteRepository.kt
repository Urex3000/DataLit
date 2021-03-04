package com.example.datalit.SQlite

import androidx.lifecycle.LiveData

class FavoriteRepository(private val daoClass: DaoClass) {

    val readAllData: LiveData<List<EntityClass>> = daoClass.getAll()

    fun addBookToFavorite(bookFav: EntityClass) {
        daoClass.insert(bookFav)
    }
}