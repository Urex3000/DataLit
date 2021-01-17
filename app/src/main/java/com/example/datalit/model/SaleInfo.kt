package com.example.datalit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SaleInfo(
    @SerializedName("buyLink")
    val buyLink: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("isEbook")
    val isEbook: Boolean?,
    @SerializedName("listPrice")
    val listPrice: ListPrice?,
    @SerializedName("offers")
    val offers: List<Offer>?,
    @SerializedName("retailPrice")
    val retailPrice: RetailPrice?,
    @SerializedName("saleability")
    val saleability: String?
) : Parcelable