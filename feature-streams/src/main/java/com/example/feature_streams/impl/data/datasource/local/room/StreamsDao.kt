package com.example.feature_streams.impl.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_streams.impl.data.datasource.local.model.StreamLocal
import kotlinx.coroutines.flow.Flow

@Dao
internal interface StreamsDao {
    @Query(
        value = """
            SELECT * FROM streams_table
            ORDER BY name
        """,
    )
    fun getAllStreams(): Flow<List<StreamLocal>>

    @Query(
        value = """
            SELECT * FROM streams_table
            WHERE is_subscribed = 1
            ORDER BY name
        """,
    )
    fun getSubscribedStreams(): Flow<List<StreamLocal>>

    @Query(
        value = """
            SELECT * FROM streams_table
            WHERE name LIKE '%' || :query || '%'
            ORDER BY name
        """,
    )
    suspend fun search(query: String): List<StreamLocal>

    @Query(
        value = """
            SELECT * FROM streams_table
            WHERE id = :streamId
        """,
    )
    suspend fun getStream(streamId: Int): StreamLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<StreamLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(value: StreamLocal)

    @Query(
        value = """
            DELETE FROM streams_table
        """,
    )
    suspend fun deleteAll()
}