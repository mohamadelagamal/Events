package com.example.domain.repos

import com.example.domain.model.SourcesItemDTO


interface SourceRepository {
    suspend fun getSources(category:String):List<SourcesItemDTO?>?
}
interface SourcesOnlineDataSource{
    suspend fun getSources(category:String):List<SourcesItemDTO?>?
}
interface SourcesOfflineDataSource{
    // get data from catch (Room)
    suspend fun updateSources(sources:List<SourcesItemDTO?>?)
    suspend fun getSourcesByCategory(category: String): List<SourcesItemDTO?>?
}