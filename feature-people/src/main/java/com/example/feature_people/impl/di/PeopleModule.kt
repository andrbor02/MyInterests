package com.example.feature_people.impl.di

import android.content.Context
import com.example.feature_people.api.PeopleComponent
import com.example.feature_people.impl.data.datasource.local.UsersLocalDataSource
import com.example.feature_people.impl.data.datasource.local.impl.UsersLocalDataSourceImpl
import com.example.feature_people.impl.data.datasource.local.room.RoomUsersDatabase
import com.example.feature_people.impl.data.datasource.local.room.UsersDao
import com.example.feature_people.impl.data.datasource.remote.UsersRemoteDataSource
import com.example.feature_people.impl.data.datasource.remote.impl.UsersRemoteRemoteDatasourceImpl
import com.example.feature_people.impl.data.repository_impl.PeopleRepositoryImpl
import com.example.feature_people.impl.data.utils.UserLocalToDomainMapper
import com.example.feature_people.impl.data.utils.UserLocalToDomainMapperImpl
import com.example.feature_people.impl.data.utils.UserWithPresenceNetworkToLocalMapper
import com.example.feature_people.impl.data.utils.UserWithPresenceNetworkToLocalMapperImpl
import com.example.feature_people.impl.domain.repository.PeopleRepository
import com.example.feature_people.impl.domain.usecase.GetPeopleUseCase
import com.example.feature_people.impl.domain.usecase.SearchPeopleUseCase
import com.example.feature_people.impl.domain.usecase.UpdatePeopleUseCase
import com.example.feature_people.impl.domain.usecase.impl.GetPeopleUseCaseImpl
import com.example.feature_people.impl.domain.usecase.impl.SearchPeopleUseCaseImpl
import com.example.feature_people.impl.domain.usecase.impl.UpdatePeopleUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        PeopleModule.Bindings::class
    ]
)
internal class PeopleModule {

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
        fun bindPeopleComponent(peopleComponentImpl: PeopleComponentImpl): PeopleComponent

        @Binds
        fun bindGetPeopleUseCase(getPeopleUseCaseImpl: GetPeopleUseCaseImpl): GetPeopleUseCase

        @Binds
        fun bindUpdatePeopleUseCase(updatePeopleUseCaseImpl: UpdatePeopleUseCaseImpl): UpdatePeopleUseCase

        @Binds
        fun bindSearchPeopleUseCase(searchPeopleUseCaseImpl: SearchPeopleUseCaseImpl): SearchPeopleUseCase

        @Binds
        fun bindPeopleRepository(peopleRepositoryImpl: PeopleRepositoryImpl): PeopleRepository

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