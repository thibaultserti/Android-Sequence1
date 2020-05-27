package fr.ec.todolist.database.user

import androidx.room.*
import com.google.gson.Gson
import fr.ec.todolist.database.todolist.TodoList

@Entity(tableName = "user")
data class User(
    @PrimaryKey val pseudo: String,
    @ColumnInfo(name = "listes") val listes: List<TodoList>? = listOf(TodoList("Liste1", pseudo))
)
{


}