package com.theworld.androidtemplatewithnavcomponents.data.stringer.request


import com.google.gson.annotations.SerializedName

data class CreateStringerRequestData(
    @SerializedName("Address")
    val address: String,
    @SerializedName("Age")
    val age: Int,
    @SerializedName("CloseTiming")
    val closeTiming: String,
    @SerializedName("InsertedBy")
    val insertedBy: Int = 10,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Password")
    val password: String,
    @SerializedName("PhoneNumber")
    val phoneNumber: String,
    @SerializedName("ShopID")
    val shopID: Int = 1,
    @SerializedName("StartTiming")
    val startTiming: String,
    @SerializedName("UpdatedBy")
    val updatedBy: Int = 10
)