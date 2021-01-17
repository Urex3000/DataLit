package com.example.datalit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Epub(
    @SerializedName("acsTokenLink")
    val acsTokenLink: String?,
    @SerializedName("downloadLink")
    val downloadLink: String?,
    @SerializedName("isAvailable")
    val isAvailable: Boolean?
) : Parcelable