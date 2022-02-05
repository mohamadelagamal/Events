package events.show.API

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class API_Manager {

    companion object{

        private var retrofit:Retrofit?=null
        private fun getInstance():Retrofit{
            if(retrofit==null){
                val logging = HttpLoggingInterceptor { message -> Log.e("api", message) }
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
                retrofit=Retrofit.Builder().baseUrl("https://newsapi.org/").client(client).
                addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!
        }
    fun getApis():WebServices{
        return getInstance().create(WebServices::class.java)
    }
    }
}