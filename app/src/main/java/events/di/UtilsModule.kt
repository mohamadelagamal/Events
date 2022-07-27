package events.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import events.NetWorkHandlerImpl
import events.NetworkHandler

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule{
    @Provides
    fun provideNetworkHandler(@ApplicationContext context: Context):NetworkHandler{
        return NetWorkHandlerImpl(context)
    }

}