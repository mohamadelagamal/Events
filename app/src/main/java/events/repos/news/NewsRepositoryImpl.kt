package events.repos.news

import events.model.ArticlesItem

class NewsRepositoryImpl ( val newsOnlineDataSource: NewsOnlineDataSource) : NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {
        //.. get data
       try {
           val result = newsOnlineDataSource.getNewsBySourceId(sourceId)
           return result
       }    catch (ex:Exception){
           throw ex
       }
    }

}