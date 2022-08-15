package com.example.domain.repos

import com.example.domain.model.ArticlesItemDTO


//.. this genrall data source
interface NewsRepository {
    suspend fun getNews(sourceId:String):List<ArticlesItemDTO?>?
}
interface NewsOnlineDataSource{
    suspend fun getNewsBySourceId(sourceId: String):List<ArticlesItemDTO?>?
}

