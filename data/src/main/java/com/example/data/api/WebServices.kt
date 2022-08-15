package com.example.data.api

import com.example.data.model.AllResponse
import com.example.data.model.ResponseBBCnews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {


    @GET("v2/top-headlines/sources")
    // used coroutines
    suspend  fun getSources (
        // look you must write the value right in "" in query parameter
        @Query("apikey") apikey: String,
        @Query("category") category: String
    ): AllResponse
    @GET("v2/everything")
    suspend fun getNewsSources(
        @Query("apiKey") apikey: String,
        @Query ("sources")sources:String
    ): ResponseBBCnews
    //.....we delete Call<REsponseBBCnews>
}