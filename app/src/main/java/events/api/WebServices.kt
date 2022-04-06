package events.api

import events.model.AllResponse
import events.model.ResponseBBCnews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {


    @GET("v2/top-headlines/sources")
    fun getSources (
        // look you must write the value right in "" in query parameter
        @Query("apikey") apikey: String,
        @Query("category") category: String
    ):Call<AllResponse>
    @GET("v2/everything")
    fun getNewsSources(
        @Query("apiKey") apikey: String,
        @Query ("sources")sources:String
    ):Call<ResponseBBCnews>
}