package events.repos.source

import events.model.SourcesItem
import events.room.MyDataBase

class SourcesOfflineDataSourceImpl (val myDataBase: MyDataBase):SourcesOfflineDataSource{
    override suspend fun updateSources(sources: List<SourcesItem?>?) {
        myDataBase.SourcesDao().updateSources(sources)
    }

    override suspend fun getSourcesByCategory(category: String): List<SourcesItem> {
        //.. get sources form database
        return myDataBase.SourcesDao().getSourcesByCategoryId(category)
    }

}