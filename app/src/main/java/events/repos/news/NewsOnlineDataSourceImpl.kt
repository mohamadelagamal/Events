package events.repos.news

import events.api.WebServices
import events.model.ArticlesItem
import events.ui.Constants

// this layer between reposatory and database
class NewsOnlineDataSourceImpl(val webServices: WebServices) : NewsOnlineDataSource,NewsRepository {
    override suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItem?>? {
        try {
        // get data
       val result= webServices.getNewsSources(Constants.apiKey,sourceId)
        return result.articles
        }catch (ex:Exception){
            throw ex
        }
    }

    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {
        TODO("Not yet implemented")
    }

}