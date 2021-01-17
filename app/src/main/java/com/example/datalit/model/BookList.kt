package com.example.datalit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookList(
    @SerializedName("items")
    val items: List<BookItem>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int
) : Parcelable