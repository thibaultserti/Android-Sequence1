package fr.ec.todolist.database.user

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE pseudo = (:pseudo)")
    fun getUser(pseudo: String): User

    @Query("DELETE FROM user")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(vararg users: User)

    @Delete
    fun delete(user: User)

}