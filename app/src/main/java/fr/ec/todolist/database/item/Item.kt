package fr.ec.todolist.database.item


import androidx.room.Entity

@Entity(tableName = "item", primaryKeys = ["owner", "liste","name"])
data class Item(
    val owner: String,
    val liste: String,
    val name: String

)