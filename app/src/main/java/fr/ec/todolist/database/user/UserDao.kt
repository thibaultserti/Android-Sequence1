package fr.ec.todolist.database.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.ec.todolist.database.user.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("DELETE FROM user")
    fun clear()

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

}