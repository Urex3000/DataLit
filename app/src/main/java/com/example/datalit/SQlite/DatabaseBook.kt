package com.example.datalit.SQlite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.datalit.model.BookItem
import com.example.datalit.model.ImageLinks
import com.example.datalit.model.VolumeInfo

@Entity(tableName = "fav_table")
data class DatabaseBook(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val authors: String?,
    val categories: String?,
    val description: String?,
    val smallThumbnail: String?,
    val thumbnail: String?,
    val pageCount: Int?,
    val previewLink: String?,
    val publishedDate: String?,
    val publisher: String?,
    val title: String?
) {
    fun toBookItem(): BookItem {
        return BookItem(
            accessInfo = null,
            null,
            null,
            null,
            null,
            null,
            null,
            VolumeInfo(
                null,
                listOf(authors),
                null,
                null,
                listOf(categories),
                null,
                description,
                ImageLinks(smallThumbnail, thumbnail),
                null,
                null,
                null,
                null,
                pageCount,
                null,
                previewLink,
                null,
                publishedDate,
                publisher,
                null,
                null,
                null,
                title
            )
        )
    }
}