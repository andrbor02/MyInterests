package com.example.feature_people.impl.data.datasource.local.impl

import com.example.feature_people.impl.data.datasource.local.UsersLocalDataSource
import com.example.feature_people.impl.data.datasource.local.model.UserLocal
import com.example.feature_people.impl.data.datasource.local.room.UsersDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class UsersLocalDataSourceImpl @Inject constructor(
    private val usersDao: UsersDao,
) : UsersLocalDataSource {
    override fun getUsers(): Flow<List<UserLocal>> {
        return usersDao.getList()
    }

    override suspend fun searchPeople(query: String): List<UserLocal> {
        return usersDao.search(query)
    }

    override suspend fun insertUsers(list: List<UserLocal>) {
        usersDao.insertList(list)
    }

    override suspend fun deleteAll() {
        usersDao.deleteAll()
    }
}