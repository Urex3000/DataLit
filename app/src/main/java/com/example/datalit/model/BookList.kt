package com.example.datalit.model


import com.google.gson.annotations.SerializedName

data class BookList(
    @SerializedName("items")
    val items: List<BookItem>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int
)