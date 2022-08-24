package com.example.data.room

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyDataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context):MyDataBase{
         MyDataBase.init(context)
        return MyDataBase.getInstance()
    }
}