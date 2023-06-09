package com.example.feature_people.impl.data.datasource.local

import com.example.feature_people.impl.data.datasource.local.model.UserLocal
import kotlinx.coroutines.flow.Flow

internal interface UsersLocalDataSource {

    fun getUsers(): Flow<List<UserLocal>>

    suspend fun searchPeople(query: String): List<UserLocal>

    suspend fun insertUsers(list: List<UserLocal>)

    suspend fun deleteAll()
}