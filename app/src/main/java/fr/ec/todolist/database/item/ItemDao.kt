package fr.ec.todolist.database.item

import androidx.room.*

@Dao
interface ItemDao {
    @Query("SELECT * FROM item")
    fun getAll(): List<Item>

    @Query("SELECT liste FROM item WHERE owner = (:pseudo)")
    fun getLists(pseudo: String): List<String>

    @Query("DELETE FROM item")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItems(vararg items: Item)

    @Delete
    fun delete(item: Item)

}