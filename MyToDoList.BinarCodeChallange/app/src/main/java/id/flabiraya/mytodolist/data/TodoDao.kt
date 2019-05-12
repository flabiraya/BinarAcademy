package id.flabiraya.mytodolist.data

import android.arch.persistence.room.*
import id.flabiraya.mytodolist.ToDoItem

@Dao
interface TodoDao{
    /**
     * SELECT -> This retrieve rows from a table in a database
     * FROM -> You specify the table to retrieve the rows from
     * ORDER BY -> This is just a sort algorithm
     * ASC -> Ascending order
     * WHERE -> This is a condition used to query data
     * */
    @Query("SELECT*FROM todo ORDER BY tId ASC")
    fun getTodoList(): List<ToDoItem>


    @Query("SELECT*FROM todo WHERE tId=:tid")
    fun getTodoItem(tid: Int): ToDoItem
    /**
     * @param todo is what we want to save in our database
     * so many conflict can occur when a data is to be saved, the strategy is used to handle such conflicts
     * Abort -> this aborts the transaction
     * Ignore -> this ignores and continues the transaction
     * Replace -> this replace the data
     * others includes fail, and roolback
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveTodo(todo: ToDoItem)

    @Update
    fun updateTodo(todo: ToDoItem)

    @Delete
    fun removeTodo(todo: ToDoItem)
}
