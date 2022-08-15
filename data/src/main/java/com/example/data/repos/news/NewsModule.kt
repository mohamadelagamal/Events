package com.example.data.repos.news

import com.example.data.api.WebServices
import com.example.domain.repos.NewsOnlineDataSource
import com.example.domain.repos.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)

class NewsModule {

    @Provides
    fun provideNewsRepo(newsOnlineDataSource: NewsOnlineDataSource): NewsRepository {
        return NewsRepositoryImpl(newsOnlineDataSource)
    }
    @Provides
    fun provideNewsOnlineDataSource(webServices: WebServices):NewsOnlineDataSource{
        return NewsOnlineDataSourceImpl(webServices)
    }
}