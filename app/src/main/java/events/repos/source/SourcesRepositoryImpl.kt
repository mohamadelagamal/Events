package events.repos.source

import events.NetworkHandler
import events.model.SourcesItem

class SourcesRepositoryImpl
    (val onlineDataSource: SourcesOnlineDataSource,
     val offlineDataSource: SourcesOfflineDataSource,
     val networkHandler: NetworkHandler):SourceRepository{
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
            if(networkHandler.isOnline()){
                val result = onlineDataSource.getSources(category)
                // catch data
                offlineDataSource.updateSources(result)
                return result
            }
            return offlineDataSource.getSourcesByCategory(category)
        } catch (ex:Exception){
            return offlineDataSource.getSourcesByCategory(category)
            // throw ex
        }
    }


}