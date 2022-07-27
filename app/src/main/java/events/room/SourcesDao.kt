package events.room

import androidx.room.*
import events.model.SourcesItem

@Dao
interface SourcesDao {
    @Query("select * from sourcesitem")
    suspend fun getSources():List<SourcesItem?>?

    @Query("select * from sourcesitem where category=:category")
    suspend fun getSourcesByCategoryId(category:String):List<SourcesItem>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSources(sources:List<SourcesItem?>?)
}