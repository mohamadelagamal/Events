package com.example.data.repos.source

import com.example.domain.model.SourcesItemDTO
import com.example.domain.repos.SourceRepository
import com.example.domain.repos.SourcesOfflineDataSource
import com.example.domain.repos.SourcesOnlineDataSource
import com.example.domain.utils.NetworkHandler


class SourcesRepositoryImpl
    (val onlineDataSource: SourcesOnlineDataSource,
     val offlineDataSource: SourcesOfflineDataSource,
     val networkHandler: NetworkHandler
): SourceRepository {
    override suspend fun getSources(category: String): List<SourcesItemDTO?>? {
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