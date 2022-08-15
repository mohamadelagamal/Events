package com.example.data.di

import android.content.Context
import com.example.domain.utils.NetworkHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule{
    @Provides
    fun provideNetworkHandler(@ApplicationContext context: Context): NetworkHandler {
        return NetWorkHandlerImpl(context)
    }

}