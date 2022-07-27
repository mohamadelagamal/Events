package events.repos.news

import events.model.ArticlesItem

//.. this genrall data source
interface NewsRepository {
    suspend fun getNews(sourceId:String):List<ArticlesItem?>?
}
interface NewsOnlineDataSource{
    suspend fun getNewsBySourceId(sourceId: String):List<ArticlesItem?>?
}