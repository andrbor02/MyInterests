package com.example.feature_chat.impl.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_chat.impl.data.datasource.local.model.MessageLocal

@Dao
internal interface MessagesDao {

    @Query(
        value = """
            SELECT * FROM messages_table
            WHERE stream_id = (:streamId)
            ORDER BY timestamp
        """,
    )
    suspend fun getAllFromStream(streamId: Int): List<MessageLocal>

    @Query(
        value = """
            SELECT * FROM messages_table
            WHERE stream_id = (:streamId)
            AND topic_name = (:topicName)
            ORDER BY timestamp
        """,
    )
    suspend fun getAllFromTopic(streamId: Int, topicName: String):
            List<MessageLocal>

    @Query(
        value = """
            SELECT * FROM messages_table
            WHERE id = (:messageId)
        """,
    )
    suspend fun getMessage(messageId: Int): MessageLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(messages: List<MessageLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: MessageLocal)

    @Query(
        value = """
            SELECT COUNT(*) FROM messages_table
            WHERE stream_id = (:streamId)
        """,
    )
    suspend fun countMessagesInStream(streamId: Int): Int

    @Query(
        value = """
            DELETE FROM messages_table
        """,
    )
    suspend fun deleteAll()
}