package com.example.feature_people.impl.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_people.impl.data.datasource.local.model.UserLocal
import kotlinx.coroutines.flow.Flow

@Dao
internal interface UsersDao {

    @Query(
        value = """
            SELECT * FROM users_table
            ORDER BY timestamp DESC
        """,
    )
    fun getList(): Flow<List<UserLocal>>

    @Query(
        value = """
            SELECT * FROM users_table
            WHERE name LIKE '%' || :query || '%'
            ORDER BY timestamp DESC
        """,
    )
    suspend fun search(query: String): List<UserLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(users: List<UserLocal>)

    @Query(
        value = """
            DELETE FROM users_table
        """,
    )
    suspend fun deleteAll()
}