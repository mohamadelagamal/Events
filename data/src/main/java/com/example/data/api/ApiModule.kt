package com.example.data.api

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule{

    const val BASE_URL = "https://newsapi.org/"

    @Provides
    fun provideLoggingInterceptor():HttpLoggingInterceptor{
        val logging = HttpLoggingInterceptor(
            object :HttpLoggingInterceptor.Logger{
                override fun log(message: String) {
                    Log.e("api",message)
                }

            }
        )
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    fun provideOKHttpClient(logging:HttpLoggingInterceptor):OkHttpClient{
        val client = OkHttpClient.Builder().addInterceptor(logging).build()
        return client
    }
    @Provides
    fun provideGsonConverter():GsonConverterFactory{
        return GsonConverterFactory.create()
    }
    @Provides
    @Singleton
    fun provideRetrofit(
        gesonCoverterFactory:GsonConverterFactory,
        okHttpClient: OkHttpClient
    ):Retrofit{
        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL).addConverterFactory(gesonCoverterFactory).build()
    }
    @Provides
    fun provideWebServices(retrofit:Retrofit):WebServices{
        return retrofit.create(WebServices::class.java)
    }
}