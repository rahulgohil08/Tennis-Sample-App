package com.theworld.androidtemplatewithnavcomponents.data.stringer.request


import com.google.gson.annotations.SerializedName

data class DeleteStringerRequestData(
    @SerializedName("StringerID")
    val stringerID: Int
)