package com.route.todo_c35_sat.database

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time;
    }

    @TypeConverter
    fun toDate(time: Long): Date {
        return Date(time);
    }
}