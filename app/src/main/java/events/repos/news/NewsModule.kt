package events.repos.news

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import events.MyApplication
import events.NetWorkHandlerImpl
import events.api.WebServices
import events.repos.source.SourcesOnlineDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsRepo(newsOnlineDataSource: NewsOnlineDataSource):NewsRepository{
        return NewsRepositoryImpl(newsOnlineDataSource)
    }
    @Provides
    @Singleton
    fun provideNewsOnlineDataSource(webServices: WebServices):NewsOnlineDataSource{
        return NewsOnlineDataSourceImpl(webServices)
    }
}