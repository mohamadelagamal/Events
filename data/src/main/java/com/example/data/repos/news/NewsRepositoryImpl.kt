package com.example.data.repos.news

import com.example.domain.model.ArticlesItemDTO
import com.example.domain.repos.NewsOnlineDataSource
import com.example.domain.repos.NewsRepository

class NewsRepositoryImpl ( val newsOnlineDataSource: NewsOnlineDataSource) : NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItemDTO?>? {
        //.. get data
       try {
           val result = newsOnlineDataSource.getNewsBySourceId(sourceId)
           return result
       }    catch (ex:Exception){
           throw ex
       }
    }

}