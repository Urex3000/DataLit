package com.example.datalit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RetailPrice(
    @SerializedName("amountInMicros")
    val amountInMicros: Int,
    @SerializedName("currencyCode")
    val currencyCode: String
) : Parcelable