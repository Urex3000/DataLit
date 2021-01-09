package com.example.datalit.model


import com.google.gson.annotations.SerializedName

data class Pdf(
    @SerializedName("acsTokenLink")
    val acsTokenLink: String,
    @SerializedName("downloadLink")
    val downloadLink: String,
    @SerializedName("isAvailable")
    val isAvailable: Boolean
)