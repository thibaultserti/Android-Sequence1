package fr.ec.todolist.database.user

import androidx.room.*

@Entity(tableName = "user")
data class User(
    @PrimaryKey val pseudo: String
)