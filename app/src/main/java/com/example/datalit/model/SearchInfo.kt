package com.example.datalit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchInfo(
    @SerializedName("textSnippet")
    val textSnippet: String
) : Parcelable