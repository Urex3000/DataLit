package com.example.datalit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReadingModes(
    @SerializedName("image")
    val image: Boolean,
    @SerializedName("text")
    val text: Boolean
) : Parcelable