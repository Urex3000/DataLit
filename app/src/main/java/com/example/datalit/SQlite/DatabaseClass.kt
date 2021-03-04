package com.example.datalit.SQlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [EntityClass::class], version = 1)
abstract class DatabaseClass : RoomDatabase() {
    abstract fun daoClass(): DaoClass

    companion object {
        @Volatile
        private var INSTANCE: DatabaseClass? = null

        fun getDatabase(context: Context): DatabaseClass {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseClass::class.java,
                    "book_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}