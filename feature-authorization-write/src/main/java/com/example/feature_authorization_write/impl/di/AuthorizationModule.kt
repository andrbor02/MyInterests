package com.example.feature_authorization_write.impl.di

import android.content.Context
import com.example.feature_authorization_write.api.AuthorizationWriteComponent
import com.example.feature_authorization_write.impl.data.datasource.local.AuthorizationWriteLocalDataSource
import com.example.feature_authorization_write.impl.data.datasource.local.impl.AuthorizationWriteLocalDataSourceImpl
import com.example.feature_authorization_write.impl.data.datasource.local.room.RoomUsersDatabase
import com.example.feature_authorization_write.impl.data.datasource.local.room.UsersDao
import com.example.feature_authorization_write.impl.data.datasource.remote.AuthorizationRemoteDataSource
import com.example.feature_people.impl.data.datasource.remote.impl.UsersRemoteRemoteDatasourceImpl
import com.example.feature_authorization_write.impl.data.repository_impl.AuthorizationWriteRepositoryImpl
import com.example.feature_authorization_write.impl.data.utils.UserLocalToDomainMapper
import com.example.feature_authorization_write.impl.data.utils.UserLocalToDomainMapperImpl
import com.example.feature_authorization_write.impl.data.utils.UserWithPresenceNetworkToLocalMapper
import com.example.feature_authorization_write.impl.data.utils.UserWithPresenceNetworkToLocalMapperImpl
import com.example.feature_authorization_write.impl.domain.repository.AuthorizationWriteRepository
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
        fun bindPeopleComponent(authorizationComponentImpl: AuthorizationWriteComponentImpl): AuthorizationWriteComponent

        @Binds
        fun bindGetPeopleUseCase(getPeopleUseCaseImpl: GetPeopleUseCaseImpl): GetPeopleUseCase

        @Binds
        fun bindUpdatePeopleUseCase(updatePeopleUseCaseImpl: UpdatePeopleUseCaseImpl): UpdatePeopleUseCase

        @Binds
        fun bindSearchPeopleUseCase(searchPeopleUseCaseImpl: SearchPeopleUseCaseImpl): SearchPeopleUseCase

        @Binds
        fun bindPeopleRepository(peopleRepositoryImpl: AuthorizationWriteRepositoryImpl): AuthorizationWriteRepository

        @Binds
        fun bindUsersRemoteDataSource(usersRemoteRemoteDatasourceImpl: UsersRemoteRemoteDatasourceImpl): AuthorizationRemoteDataSource

        @Binds
        fun bindUsersLocalDataSource(authorizationWriteLocalDataSourceImpl: AuthorizationWriteLocalDataSourceImpl): AuthorizationWriteLocalDataSource

        @Binds
        fun bindUserWithPresenceNetworkToLocalMapper(usersWithPresenceNetworkToLocalMapperImpl: UserWithPresenceNetworkToLocalMapperImpl): UserWithPresenceNetworkToLocalMapper

        @Binds
        fun bindUserLocalToDomainMapper(userLocalToDomainMapperImpl: UserLocalToDomainMapperImpl): UserLocalToDomainMapper
    }
}