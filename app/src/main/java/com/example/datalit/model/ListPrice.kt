package com.example.datalit.model


import com.google.gson.annotations.SerializedName

data class ListPrice(
    @SerializedName("amount")
    val amount: Float,
    @SerializedName("currencyCode")
    val currencyCode: String
)