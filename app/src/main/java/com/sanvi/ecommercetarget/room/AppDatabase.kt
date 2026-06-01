package com.sanvi.ecommercetarget.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sanvi.ecommercetarget.screen.home.Model.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // Define dao here to interact with database

    abstract fun cartDao(): CartDao

    // Singleton db instance
    companion object {
        @Volatile // Ensures visibility across the threads
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if INSTANCE is not null ===> return it
            // if INSTANCE is null ==> execute the code inside
            // the synchronized block to create the db instance
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabase::class.java,
                    name = "cart_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}