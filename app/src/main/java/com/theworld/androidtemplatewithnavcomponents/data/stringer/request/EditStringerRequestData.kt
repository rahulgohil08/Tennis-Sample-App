package com.theworld.androidtemplatewithnavcomponents.data.stringer.request


import com.google.gson.annotations.SerializedName

data class EditStringerRequestData(
    @SerializedName("Address")
    val address: String,
    @SerializedName("Age")
    val age: Int,
    @SerializedName("CloseTiming")
    val closeTiming: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Password")
    val password: String,
    @SerializedName("PhoneNumber")
    val phoneNumber: String,
    @SerializedName("StartTiming")
    val startTiming: String,
    @SerializedName("StringerID")
    val stringerID: Int,
    @SerializedName("UpdatedBy")
    val updatedBy: Int = 10
)