package com.example.feature_people.impl.data.datasource.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.feature_people.impl.data.datasource.local.model.UserLocal

@Database(
    entities = [UserLocal::class],
    version = 1,
    exportSchema = true
)
internal abstract class RoomUsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object {

        @Volatile
        private var INSTANCE: RoomUsersDatabase? = null

        fun getDatabase(
            context: Context,
        ): RoomUsersDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomUsersDatabase::class.java,
                    UserLocal.TABLE_NAME
                )
                    .fallbackToDestructiveMigration() // we can turn it because data is just cache
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}