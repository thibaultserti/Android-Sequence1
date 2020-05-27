package fr.ec.todolist.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "pseudo") val pseudo: String?
    //@ColumnInfo(name = "listes") val listes: List<String>? = listOf("A")
)