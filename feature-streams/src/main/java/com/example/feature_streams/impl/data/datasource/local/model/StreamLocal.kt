package com.example.feature_streams.impl.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = StreamLocal.TABLE_NAME)
internal data class StreamLocal(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "is_subscribed")
    val isSubscribed: Boolean,

    @ColumnInfo(name = "topics")
    val topics: String,
) {
    companion object {
        const val TABLE_NAME = "streams_table"
    }
}