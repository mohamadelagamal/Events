package com.example.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.SourcesItem

@Database (entities = [SourcesItem::class], version = 1, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun SourcesDao():SourcesDao


    companion object{
        var database:MyDataBase?=null
        const val DATABASE_NAME ="newsdatabase"
        fun init(context:Context):MyDataBase{
            if (database == null ){
                database = Room.databaseBuilder(context , MyDataBase::class.java , DATABASE_NAME)
                    .fallbackToDestructiveMigration().build()
            }
            return database!!
        }
        fun getInstance():MyDataBase{
            return database !!
        }
    }

}