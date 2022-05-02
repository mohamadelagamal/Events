package app_note.model.dao

import androidx.room.*
import app_note.model.entity.Todo
import java.util.*

@Dao
interface TodoDao {
    @Insert
    fun addTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Query("select * from Todo")
    fun getAllTodos(): List<Todo>

    @Query("select * from Todo where date =:date ")
    fun getTodosByDate(date: Date): List<Todo>
}