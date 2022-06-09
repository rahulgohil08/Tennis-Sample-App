package com.theworld.androidtemplatewithnavcomponents.data.stringer.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stringer(
    @SerializedName("Address")
    val address: String = "",
    @SerializedName("Age")
    val age: Int = 0,
    @SerializedName("CloseTiming")
    val closeTiming: String = "",
    @SerializedName("Name")
    val name: String = "",
    @SerializedName("Password")
    val password: String = "",
    @SerializedName("PhoneNumber")
    val phoneNumber: String = "",
    @SerializedName("StartTiming")
    val startTiming: String = "",
    @SerializedName("StringerID")
    val stringerID: Int = 0
) : Parcelable