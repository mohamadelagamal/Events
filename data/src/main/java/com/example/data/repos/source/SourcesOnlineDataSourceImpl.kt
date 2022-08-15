package com.example.data.repos.source

import com.example.data.api.WebServices
import com.example.data.model.convertTo
import com.example.domain.model.AllResponseDTO
import com.example.domain.model.SourcesItemDTO
import com.example.domain.repos.SourcesOnlineDataSource


// API
const val apiKey="834d53b59f4f4bd4a56fc30399e17be2"

class SourcesOnlineDataSourceImpl (val webServices: WebServices): SourcesOnlineDataSource {
    override suspend fun getSources(category: String): List<SourcesItemDTO?>? {
        try {
        // problem in this
            val result = webServices.getSources(apiKey,category)
            return result.convertTo(AllResponseDTO::class.java).sources
        }catch (ex:Exception){
            throw ex
        }
    }
}