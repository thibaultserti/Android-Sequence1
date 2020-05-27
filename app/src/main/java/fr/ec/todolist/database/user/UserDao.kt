package fr.ec.todolist.database.user

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT listes FROM user WHERE pseudo = (:pseudo)")
    fun getListe(pseudo: String): List<String>

    @Query("DELETE FROM user")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

}