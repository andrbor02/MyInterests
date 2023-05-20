package com.example.feature_profile.impl.di

import com.example.feature_profile.api.ProfileComponent
import com.example.feature_profile.impl.data.datasource.remote.ProfileRemoteDataSource
import com.example.feature_profile.impl.data.datasource.remote.impl.ProfileRemoteDatasourceImpl
import com.example.feature_profile.impl.data.repository_impl.ProfileRepositoryImpl
import com.example.feature_profile.impl.data.utils.UserWithPresenceNetworkMerger
import com.example.feature_profile.impl.data.utils.UserWithPresenceNetworkMergerImpl
import com.example.feature_profile.impl.domain.repository.ProfileRepository
import com.example.feature_profile.impl.domain.usecase.GetProfileUseCase
import com.example.feature_profile.impl.domain.usecase.impl.GetProfileUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        ProfileModule.Bindings::class]
)
internal class ProfileModule {

    @Module
    interface Bindings {
        @Binds
        fun bindProfileComponent(profileComponentImpl: ProfileComponentImpl): ProfileComponent

        @Binds
        fun bindGetProfileUseCase(getProfileUseCaseImpl: GetProfileUseCaseImpl): GetProfileUseCase

        @Binds
        fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

        @Binds
        fun provideUsersRemoteDataSource(profileRemoteDatasourceImpl: ProfileRemoteDatasourceImpl): ProfileRemoteDataSource

        @Binds
        fun bindUserWithPresenceNetworkMerger(userWithPresenceNetworkMergerImpl: UserWithPresenceNetworkMergerImpl): UserWithPresenceNetworkMerger
    }
}