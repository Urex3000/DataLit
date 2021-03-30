package com.example.datalit.SQlite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoClass {


    @Query("SELECT * FROM fav_table ORDER BY id ASC")
    fun getAll(): LiveData<List<DatabaseBook>>

    @Insert
    fun insert(databaseBook: DatabaseBook)

    @Delete
    fun delete(databaseBook: DatabaseBook)

    @Query("DELETE FROM fav_table")
    fun deleteAll()
}