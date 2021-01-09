package com.example.datalit.model


import com.google.gson.annotations.SerializedName

data class SaleInfo(
    @SerializedName("buyLink")
    val buyLink: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("isEbook")
    val isEbook: Boolean,
    @SerializedName("listPrice")
    val listPrice: ListPrice,
    @SerializedName("offers")
    val offers: List<Offer>,
    @SerializedName("retailPrice")
    val retailPrice: RetailPrice,
    @SerializedName("saleability")
    val saleability: String
)