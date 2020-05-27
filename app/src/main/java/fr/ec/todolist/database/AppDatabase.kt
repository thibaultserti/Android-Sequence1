package fr.ec.todolist.database

import android.content.Context
import androidx.room.*
import fr.ec.todolist.database.user.User
import fr.ec.todolist.database.user.UserDao
import fr.ec.todolist.utilities.Converters

@Database(entities = [User::class], version = 1)
@TypeConverters(Converters::class)
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

