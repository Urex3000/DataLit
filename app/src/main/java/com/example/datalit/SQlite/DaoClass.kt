package com.example.datalit.SQlite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoClass {


    @Query("SELECT * FROM fav_table ORDER BY id ASC")
    fun getAll(): LiveData<List<EntityClass>>

    @Insert
    fun insert(entityClass: EntityClass)

    @Insert
    fun delete(entityClass: EntityClass)
}