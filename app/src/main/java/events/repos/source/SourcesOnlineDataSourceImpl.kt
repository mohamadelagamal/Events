package events.repos.source

import events.api.WebServices
import events.model.SourcesItem
import events.ui.Constants

// API
class SourcesOnlineDataSourceImpl (val webServices: WebServices):SourcesOnlineDataSource {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
        // problem in this
            val result = webServices.getSources(Constants.apiKey,category)
            return result.sources
        }catch (ex:Exception){
            throw ex
        }
    }
}