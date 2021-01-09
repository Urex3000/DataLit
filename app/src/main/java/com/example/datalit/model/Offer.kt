package com.example.datalit.model


import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("finskyOfferType")
    val finskyOfferType: Int,
    @SerializedName("listPrice")
    val listPrice: ListPrice,
    @SerializedName("retailPrice")
    val retailPrice: RetailPrice
)