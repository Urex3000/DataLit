package com.example.datalit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IndustryIdentifier(
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("type")
    val type: String
) : Parcelable