package com.theworld.androidtemplatewithnavcomponents.network

import com.theworld.androidtemplatewithnavcomponents.data.stringer.request.CreateStringerRequestData
import com.theworld.androidtemplatewithnavcomponents.data.stringer.request.DeleteStringerRequestData
import com.theworld.androidtemplatewithnavcomponents.data.stringer.request.EditStringerRequestData
import com.theworld.androidtemplatewithnavcomponents.data.stringer.response.Stringer
import okhttp3.ResponseBody
import retrofit2.http.*

interface Api {

    /*------------------------------------ CRUD Stringer ------------------------------------*/

    @POST("stringerDetail")
    suspend fun createStringer(@Body data: CreateStringerRequestData): ResponseBody

    @GET("stringerlist")
    suspend fun fetchStringers(): List<Stringer>

    @PUT("stringerDetail")
    suspend fun updateStringer(@Body data: EditStringerRequestData): ResponseBody

    //    @DELETE("stringerDetail")
    @HTTP(method = "DELETE", path = "stringerDetail", hasBody = true)
    suspend fun deleteStringer(@Body data: DeleteStringerRequestData): ResponseBody

}
