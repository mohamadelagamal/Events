package com.example.data.repos.source

import com.example.data.api.WebServices
import com.example.data.room.MyDataBase
import com.example.domain.repos.SourceRepository
import com.example.domain.repos.SourcesOfflineDataSource
import com.example.domain.repos.SourcesOnlineDataSource
import com.example.domain.utils.NetworkHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SourcesModule{

    @Provides
    @Singleton
    fun provideOnlineDataSource(webServices: WebServices): SourcesOnlineDataSource {
        //.. return concrete object
        return SourcesOnlineDataSourceImpl(webServices)
    }
    @Provides
    @Singleton
    // get repository
    fun provideOfflineDataSource(dataBase: MyDataBase): SourcesOfflineDataSource {
        return SourcesOfflineDataSourceImpl(dataBase)
    }
    @Singleton
    @Provides
    fun provideDatabase():MyDataBase{
        return MyDataBase.getInstance()
    }

    @Provides
    fun provideSourcesRepo(onlineDataSource: SourcesOnlineDataSource,
                           offlineDataSource: SourcesOfflineDataSource,
                           networkHandler: NetworkHandler
    ): SourceRepository {
        return SourcesRepositoryImpl(onlineDataSource,offlineDataSource,networkHandler)
    }

}