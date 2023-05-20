package com.example.feature_streams.impl.data.datasource.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.feature_streams.impl.data.datasource.local.model.StreamLocal

@Database(
    entities = [StreamLocal::class],
    version = 1,
    exportSchema = true
)
internal abstract class RoomStreamsDatabase : RoomDatabase() {

    abstract fun streamsDao(): StreamsDao

    companion object {

        @Volatile
        private var INSTANCE: RoomStreamsDatabase? = null

        fun getDatabase(
            context: Context,
        ): RoomStreamsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomStreamsDatabase::class.java,
                    StreamLocal.TABLE_NAME
                )
                    .fallbackToDestructiveMigration() // we can turn it because data is just cache
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}