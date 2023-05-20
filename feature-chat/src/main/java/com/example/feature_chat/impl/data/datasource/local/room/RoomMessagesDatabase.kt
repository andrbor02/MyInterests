package com.example.feature_chat.impl.data.datasource.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.feature_chat.impl.data.datasource.local.model.MessageLocal

@Database(
    entities = [MessageLocal::class],
    version = 6,
    exportSchema = true
)
internal abstract class RoomMessagesDatabase : RoomDatabase() {

    abstract fun messagesDao(): MessagesDao

    companion object {
        @Volatile
        private var INSTANCE: RoomMessagesDatabase? = null

        fun getDatabase(
            context: Context,
        ): RoomMessagesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomMessagesDatabase::class.java,
                    MessageLocal.TABLE_NAME
                )
                    .fallbackToDestructiveMigration() // we can turn it because data is just cache
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}