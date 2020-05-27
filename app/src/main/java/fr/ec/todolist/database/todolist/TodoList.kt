package fr.ec.todolist.database.todolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todoliste")
data class TodoList(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "owner") val owner: String,
    @ColumnInfo(name = "items") val items: String = "item1"

)
