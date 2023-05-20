package com.example.feature_authorization_write.impl.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserLocal.TABLE_NAME)
internal data class UserLocal(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "status")
    val status: StatusLocal,

    @ColumnInfo(name = "timestamp")
    val timestamp: Int,

    @ColumnInfo(name = "email")
    val email: String,
) {
    companion object {
        const val TABLE_NAME = "users_table"
    }
}