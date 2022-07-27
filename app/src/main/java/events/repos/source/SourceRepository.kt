package events.repos.source

import events.model.SourcesItem

interface SourceRepository {
    suspend fun getSources(category:String):List<SourcesItem?>?
}
interface SourcesOnlineDataSource{
    suspend fun getSources(category:String):List<SourcesItem?>?
}
interface SourcesOfflineDataSource{
    // get data from catch (Room)
    suspend fun updateSources(sources:List<SourcesItem?>?)
    suspend fun getSourcesByCategory(category: String):List<SourcesItem>
}