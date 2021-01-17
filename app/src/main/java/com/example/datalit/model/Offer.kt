package com.example.datalit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Offer(
    @SerializedName("finskyOfferType")
    val finskyOfferType: Int,
    @SerializedName("listPrice")
    val listPrice: ListPrice,
    @SerializedName("retailPrice")
    val retailPrice: RetailPrice
) : Parcelable