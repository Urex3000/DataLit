package com.example.datalit.SQlite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class EntityClass(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "Автор(ы)")
    val authors: String?,
    @ColumnInfo(name = "Категория")
    val categories: String?,
    @ColumnInfo(name = "Описание")
    val description: String?,
    @ColumnInfo(name = "М. картинка")
    val smallThumbnail: String?,
    @ColumnInfo(name = "Б. картинка")
    val thumbnail: String?,
    @ColumnInfo(name = "Кол-во страниц")
    val pageCount: Int?,
    @ColumnInfo(name = "Ссылка")
    val previewLink: String?,
    @ColumnInfo(name = "Дата публикации")
    val publishedDate: String?,
    @ColumnInfo(name = "Публикация")
    val publisher: String?,
    @ColumnInfo(name = "Название")
    val title: String?
)