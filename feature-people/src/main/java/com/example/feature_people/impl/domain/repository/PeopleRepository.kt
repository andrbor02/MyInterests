package com.example.feature_people.impl.domain.repository

import com.example.feature_people.impl.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

internal interface PeopleRepository {

    fun getPeople(): Flow<List<UserModel>>

    suspend fun updatePeople()

    suspend fun searchPeople(query: String): List<UserModel>

    suspend fun clearDatabase()
}