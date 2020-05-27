package fr.ec.todolist.utilities

import androidx.room.TypeConverter
import com.google.gson.Gson
import fr.ec.todolist.database.todolist.TodoList

class Converters {
    @TypeConverter
    fun listToJson(value: List<*>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToTodoList(value: String) = Gson().fromJson(value, Array<TodoList>::class.java).toList()

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}