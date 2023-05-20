package com.example.feature_authorization_read.impl.di

import android.content.Context
import com.example.`feature_authorization-read`.api.AuthorizationComponent
import com.example.feature_people.impl.data.datasource.local.UsersLocalDataSource
import com.example.feature_people.impl.data.datasource.local.impl.UsersLocalDataSourceImpl
import com.example.feature_people.impl.data.datasource.local.room.RoomUsersDatabase
import com.example.feature_people.impl.data.datasource.local.room.UsersDao
import com.example.feature_people.impl.data.datasource.remote.UsersRemoteDataSource
import com.example.feature_people.impl.data.datasource.remote.impl.UsersRemoteRemoteDatasourceImpl
import com.example.`feature_authorization-read`.impl.data.repository_impl.AuthorizationRepositoryImpl
import com.example.feature_people.impl.data.utils.UserLocalToDomainMapper
import com.example.feature_people.impl.data.utils.UserLocalToDomainMapperImpl
import com.example.feature_people.impl.data.utils.UserWithPresenceNetworkToLocalMapper
import com.example.feature_people.impl.data.utils.UserWithPresenceNetworkToLocalMapperImpl
import com.example.feature_authorization_read.impl.domain.repository.AuthorizationRepository
import com.example.feature_people.impl.di.AuthorizationComponentImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        AuthorizationModule.Bindings::class
    ]
)
internal class AuthorizationModule {

    @Provides
    fun provideDatabase(context: Context): RoomUsersDatabase {
        return RoomUsersDatabase.getDatabase(
            context = context
        )
    }

    @Provides
    fun provideMessagesDao(roomUsersDatabase: RoomUsersDatabase): UsersDao {
        return roomUsersDatabase.usersDao()
    }

    @Module
    interface Bindings {

        @Binds
        fun bindPeopleComponent(authorizationComponentImpl: AuthorizationComponentImpl): AuthorizationComponent

        @Binds
        fun bindGetPeopleUseCase(getPeopleUseCaseImpl: GetPeopleUseCaseImpl): GetPeopleUseCase

        @Binds
        fun bindUpdatePeopleUseCase(updatePeopleUseCaseImpl: UpdatePeopleUseCaseImpl): UpdatePeopleUseCase

        @Binds
        fun bindSearchPeopleUseCase(searchPeopleUseCaseImpl: SearchPeopleUseCaseImpl): SearchPeopleUseCase

        @Binds
        fun bindPeopleRepository(peopleRepositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository

        @Binds
        fun bindUsersRemoteDataSource(usersRemoteRemoteDatasourceImpl: UsersRemoteRemoteDatasourceImpl): UsersRemoteDataSource

        @Binds
        fun bindUsersLocalDataSource(usersLocalDataSourceImpl: UsersLocalDataSourceImpl): UsersLocalDataSource

        @Binds
        fun bindUserWithPresenceNetworkToLocalMapper(usersWithPresenceNetworkToLocalMapperImpl: UserWithPresenceNetworkToLocalMapperImpl): UserWithPresenceNetworkToLocalMapper

        @Binds
        fun bindUserLocalToDomainMapper(userLocalToDomainMapperImpl: UserLocalToDomainMapperImpl): UserLocalToDomainMapper
    }
}