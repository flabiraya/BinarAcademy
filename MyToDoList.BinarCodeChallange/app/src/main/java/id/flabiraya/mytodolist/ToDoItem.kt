package id.flabiraya.mytodolist

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todo")
class ToDoItem {
    companion object Factory {
        fun create(): ToDoItem = ToDoItem()
    }
    @ColumnInfo(name = "todo_text")
    var itemText: String? = null
    @ColumnInfo(name = "todo_objectId")
    var objectId: String? = null
    @PrimaryKey(autoGenerate = true)
    var tId: Int = 0
}