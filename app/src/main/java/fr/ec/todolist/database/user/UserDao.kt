package fr.ec.todolist.database.user

import androidx.room.*
import fr.ec.todolist.database.todolist.TodoList

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE pseudo = (:pseudo)")
    fun getUser(pseudo: String): User

    @Query("DELETE FROM user")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

}