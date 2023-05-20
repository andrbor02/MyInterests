package com.example.feature_people.impl.data.repository_impl


import com.example.feature_people.impl.data.datasource.local.UsersLocalDataSource
import com.example.feature_people.impl.data.datasource.remote.UsersRemoteDataSource
import com.example.feature_people.impl.data.datasource.remote.model.users.UsersResponse
import com.example.feature_people.impl.data.datasource.remote.model.users_presence.UsersPresenceResponse
import com.example.feature_people.impl.data.utils.UserLocalToDomainMapper
import com.example.feature_people.impl.data.utils.UserWithPresenceNetworkToLocalMapper
import com.example.feature_people.impl.domain.model.UserModel
import com.example.feature_people.impl.domain.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PeopleRepositoryImpl @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource,
    private val usersLocalDataSource: UsersLocalDataSource,
    private val usersWithPresenceNetworkToLocalMapper: UserWithPresenceNetworkToLocalMapper,
    private val userLocalToDomainMapper: UserLocalToDomainMapper,
) : PeopleRepository {

    override fun getPeople(): Flow<List<UserModel>> =
        usersLocalDataSource.getUsers()
            .filter { it.isNotEmpty() }
            .map { listLocal ->
                listLocal.map { userLocal ->
                    userLocalToDomainMapper(userLocal)
                }
            }.flowOn(Dispatchers.Default)


    override suspend fun updatePeople() {
        val users = usersRemoteDataSource.getUsersPresence().combine(
            usersRemoteDataSource.getUsers()
        ) { usersPresence: UsersPresenceResponse, users: UsersResponse ->
            usersWithPresenceNetworkToLocalMapper(
                users = users.members,
                usersPresences = usersPresence.presences
            )
        }.flowOn(Dispatchers.Default)
            .first()

        usersLocalDataSource.insertUsers(users)
    }

    override suspend fun searchPeople(query: String): List<UserModel> {
        return usersLocalDataSource.searchPeople(query).map { userLocal ->
            withContext(Dispatchers.Default) {
                userLocalToDomainMapper(userLocal)
            }
        }
    }

    override suspend fun clearDatabase() {
        usersLocalDataSource.deleteAll()
    }
}