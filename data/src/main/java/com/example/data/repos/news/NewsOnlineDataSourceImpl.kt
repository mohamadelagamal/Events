package com.example.data.repos.news

import com.example.data.api.WebServices
import com.example.data.model.ArticlesItem
import com.example.data.model.convertTo
import com.example.domain.model.AllResponseDTO
import com.example.domain.model.ArticlesItemDTO
import com.example.domain.model.ResponseBBCnewsDTO
import com.example.domain.repos.NewsOnlineDataSource
import com.example.domain.repos.NewsRepository


// this layer between reposatory and database
const val apiKey="834d53b59f4f4bd4a56fc30399e17be2"
class NewsOnlineDataSourceImpl(val webServices: WebServices) : NewsOnlineDataSource,
    NewsRepository {
    override suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItemDTO?>? {
        try {
        // get data
        val result= webServices.getNewsSources(apiKey,sourceId)
        return result.convertTo(ResponseBBCnewsDTO::class.java).articles
        }catch (ex:Exception){
            throw ex
        }
    }

    override suspend fun getNews(sourceId: String): List<ArticlesItemDTO?>? {
        TODO("Not yet implemented")
    }


}