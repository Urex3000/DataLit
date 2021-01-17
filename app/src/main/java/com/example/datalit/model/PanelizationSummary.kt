package com.example.datalit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PanelizationSummary(
    @SerializedName("containsEpubBubbles")
    val containsEpubBubbles: Boolean,
    @SerializedName("containsImageBubbles")
    val containsImageBubbles: Boolean
) : Parcelable