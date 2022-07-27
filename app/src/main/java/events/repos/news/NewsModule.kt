package events.repos.news

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import events.MyApplication
import events.NetWorkHandlerImpl
import events.api.WebServices
import events.repos.source.SourcesOnlineDataSource

@Module
@InstallIn(SingletonComponent::class)

class NewsModule {

    @Provides
    fun provideNewsRepo(newsOnlineDataSource: NewsOnlineDataSource):NewsRepository{
        return NewsRepositoryImpl(newsOnlineDataSource)
    }
    @Provides
    fun provideNewsOnlineDataSource(webServices: WebServices):NewsOnlineDataSource{
        return NewsOnlineDataSourceImpl(webServices)
    }
}