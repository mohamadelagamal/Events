package com.example.data.repos.source

import com.example.data.model.SourcesItem
import com.example.data.model.convertTo
import com.example.data.room.MyDataBase
import com.example.domain.model.SourcesItemDTO
import com.example.domain.repos.SourcesOfflineDataSource


class SourcesOfflineDataSourceImpl (val myDataBase: MyDataBase): SourcesOfflineDataSource {
    override suspend fun updateSources(sources: List<SourcesItemDTO?>?) {
        myDataBase.SourcesDao().updateSources(sources?.let {
            it.map {
                it?.convertTo(SourcesItem::class.java)
            }
        })
    }

    override suspend fun getSourcesByCategory(category: String): List<SourcesItemDTO?> {
        //.. get sources form database
        return myDataBase.SourcesDao().getSourcesByCategoryId(category).map {
            it.convertTo(SourcesItemDTO::class.java)
        }
    }

}