package id.flabiraya.mytodolist.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import id.flabiraya.mytodolist.ToDoItem

@Database(entities = [ToDoItem::class], version = 1, exportSchema = false)
abstract class TodoListDatabase : RoomDatabase() {

    /**
     * This is an abstract method that returns a dao for the Db
     * */
    abstract fun getTodoDao(): TodoDao

    /**
     * A singleton design pattern is used to ensure that the database instance created is one
     * */
    companion object {
        val databaseName = "tododatabase"
        var todoListDatabase: TodoListDatabase? = null
        fun getInstance(context: Context): TodoListDatabase? {
            if (todoListDatabase == null) {
                todoListDatabase = Room.databaseBuilder(context,
                    TodoListDatabase::class.java,
                    TodoListDatabase.databaseName)
                    .allowMainThreadQueries()
                    .build()
            }
            return todoListDatabase
        }
    }
}