package com.example.datalit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListPrice(
    @SerializedName("amount")
    val amount: Float,
    @SerializedName("currencyCode")
    val currencyCode: String
) : Parcelable