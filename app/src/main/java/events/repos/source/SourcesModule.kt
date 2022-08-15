package events.repos.source

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import events.NetworkHandler
import events.api.WebServices
import events.repos.source.*
import events.room.MyDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourcesModule{

    @Provides
    @Singleton
    fun provideOnlineDataSource(webServices: WebServices):SourcesOnlineDataSource{
        //.. return concrete object
        return SourcesOnlineDataSourceImpl(webServices)
    }
    @Provides
    @Singleton
    // get repository
    fun provideOfflineDataSource(dataBase: MyDataBase):SourcesOfflineDataSource{
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
                           networkHandler: NetworkHandler):SourceRepository{
        return SourcesRepositoryImpl(onlineDataSource,offlineDataSource,networkHandler)
    }

}