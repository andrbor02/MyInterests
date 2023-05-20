package com.example.feature_chat.impl.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MessageLocal.TABLE_NAME)
internal data class MessageLocal(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "sender_id")
    val senderId: Int,

    @ColumnInfo(name = "avatar")
    val avatar: String?,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Int,

    @ColumnInfo(name = "topic")
    val topic: String,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "reactions")
    val reactions: String,

    @ColumnInfo(name = "stream_id")
    val streamId: Int,

    @ColumnInfo(name = "topic_name")
    val topicName: String,
) {
    companion object {
        const val TABLE_NAME = "messages_table"
    }
}