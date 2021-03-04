package com.example.datalit.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.datalit.SQlite.DatabaseClass
import com.example.datalit.SQlite.EntityClass
import com.example.datalit.SQlite.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val getAllData: LiveData<List<EntityClass>>
    private val repository: FavoriteRepository

    init {
        val daoClass = DatabaseClass.getDatabase(application).daoClass()
        repository = FavoriteRepository(daoClass)
        getAllData = repository.readAllData
    }

    fun addUser(bookFav: EntityClass) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBookToFavorite(bookFav)
        }
    }

}