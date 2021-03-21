package com.example.datalit.SQlite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoClass {


    @Query("SELECT * FROM fav_table ORDER BY id ASC")
    fun getAll(): LiveData<List<DatabaseBook>>

    @Insert
    fun insert(databaseBook: DatabaseBook)

    @Insert
    fun delete(databaseBook: DatabaseBook)
}