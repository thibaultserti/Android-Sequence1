package fr.ec.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "pseudo") val pseudo: String?,
    //@ColumnInfo(name = "listes") val listes: ArrayList<Int>?,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0

)