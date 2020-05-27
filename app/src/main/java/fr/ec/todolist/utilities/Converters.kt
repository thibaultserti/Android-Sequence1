package fr.ec.todolist.utilities

import androidx.room.TypeConverter
import com.google.gson.Gson
import fr.ec.todolist.database.todolist.TodoList

class Converters {

    @TypeConverter
    fun listToJson(value: List<TodoList>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<TodoList>::class.java).toList()
}