package fr.ec.todolist.database

import android.content.Context
import androidx.room.*

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "pseudo") val pseudo: String?,
    //@ColumnInfo(name = "listes") val listes: ArrayList<Int>?,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0

)

data class Liste(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "liste") val liste: ArrayList<String>?
)

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun UserDao(): UserDao
    companion object {

        /**
         * The only instance
         */
        private var sInstance: AppDatabase? = null

        /**
         * Gets the singleton instance of SampleDatabase.
         *
         * @param context The context.
         * @return The singleton instance of SampleDatabase.
         */
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "todolist.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }
        fun destroyInstance() {
            sInstance = null
        }
    }
}

