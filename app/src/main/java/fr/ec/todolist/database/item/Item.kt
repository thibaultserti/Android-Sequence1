package fr.ec.todolist.database.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true) val foodId: Int = 0,
    @ColumnInfo(name = "owner") val owner: String,
    @ColumnInfo(name = "liste") val liste: String,
    @ColumnInfo(name = "name") val name: String?

)