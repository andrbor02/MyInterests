package com.example.feature_streams.impl.di

import android.content.Context
import com.example.feature_streams.api.StreamsComponent
import com.example.feature_streams.impl.data.datasource.local.StreamsLocalDataSource
import com.example.feature_streams.impl.data.datasource.local.impl.StreamsLocalDataSourceImpl
import com.example.feature_streams.impl.data.datasource.local.room.RoomStreamsDatabase
import com.example.feature_streams.impl.data.datasource.local.room.StreamsDao
import com.example.feature_streams.impl.data.datasource.remote.StreamsRemoteDataSource
import com.example.feature_streams.impl.data.datasource.remote.impl.StreamsRemoteDataSourceImpl
import com.example.feature_streams.impl.data.repository_impl.StreamsRepositoryImpl
import com.example.feature_streams.impl.data.utils.StreamLocalToDomainMapper
import com.example.feature_streams.impl.data.utils.StreamLocalToDomainMapperImpl
import com.example.feature_streams.impl.data.utils.StreamRemoteToLocalMapper
import com.example.feature_streams.impl.data.utils.StreamRemoteToLocalMapperImpl
import com.example.feature_streams.impl.data.utils.TopicRemoteToDomainMapper
import com.example.feature_streams.impl.data.utils.TopicRemoteToDomainMapperImpl
import com.example.feature_streams.impl.data.utils.TopicRemoteToLocalMapper
import com.example.feature_streams.impl.data.utils.TopicRemoteToLocalMapperImpl
import com.example.feature_streams.impl.domain.repository.StreamsRepository
import com.example.feature_streams.impl.domain.usecase.CreateStreamUseCase
import com.example.feature_streams.impl.domain.usecase.GetStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.GetSubscribedStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.GetTopicsUseCase
import com.example.feature_streams.impl.domain.usecase.SearchStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateStreamsUseCase
import com.example.feature_streams.impl.domain.usecase.UpdateTopicsUseCase
import com.example.feature_streams.impl.domain.usecase.impl.CreateStreamUseCaseImpl
import com.example.feature_streams.impl.domain.usecase.impl.GetStreamsUseCaseImpl
import com.example.feature_streams.impl.domain.usecase.impl.GetSubscribedStreamsUseCaseImpl
import com.example.feature_streams.impl.domain.usecase.impl.GetTopicsUseCaseImpl
import com.example.feature_streams.impl.domain.usecase.impl.SearchStreamsUseCaseImpl
import com.example.feature_streams.impl.domain.usecase.impl.UpdateStreamsUseCaseImpl
import com.example.feature_streams.impl.domain.usecase.impl.UpdateTopicsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        StreamsModule.Bindings::class
    ]
)
internal class StreamsModule {

    @Provides
    fun provideDatabase(context: Context): RoomStreamsDatabase {
        return RoomStreamsDatabase.getDatabase(
            context = context
        )
    }

    @Provides
    fun provideStreamsDao(roomStreamsDatabase: RoomStreamsDatabase): StreamsDao {
        return roomStreamsDatabase.streamsDao()
    }

    @Module
    interface Bindings {

        @Binds
        fun bindStreamsComponent(streamsComponent: StreamsComponent): StreamsComponent

        @Binds
        fun bindGetSubscribedStreamsUseCase(getSubscribedStreamsUseCaseImpl: GetSubscribedStreamsUseCaseImpl): GetSubscribedStreamsUseCase

        @Binds
        fun bindGetStreamsUseCase(getStreamsUseCaseImpl: GetStreamsUseCaseImpl): GetStreamsUseCase

        @Binds
        fun bindUpdateTopicsUseCase(updateTopicsUseCaseImpl: UpdateTopicsUseCaseImpl): UpdateTopicsUseCase

        @Binds
        fun bindUpdateStreamsUseCase(updateStreamsUseCaseImpl: UpdateStreamsUseCaseImpl): UpdateStreamsUseCase

        @Binds
        fun bindGetTopicsUseCase(getTopicsUseCaseImpl: GetTopicsUseCaseImpl): GetTopicsUseCase

        @Binds
        fun bindSearchStreamsUseCase(searchStreamsUseCaseImpl: SearchStreamsUseCaseImpl): SearchStreamsUseCase

        @Binds
        fun bindCreateStreamUseCase(createStreamUseCaseImpl: CreateStreamUseCaseImpl): CreateStreamUseCase

        @Binds
        fun bindStreamsRepository(streamsRepositoryImpl: StreamsRepositoryImpl): StreamsRepository

        @Binds
        fun bindStreamsRemoteDataSource(streamsRemoteDatasourceImpl: StreamsRemoteDataSourceImpl): StreamsRemoteDataSource

        @Binds
        fun bindStreamsLocalDataSource(streamsLocalDataSourceImpl: StreamsLocalDataSourceImpl): StreamsLocalDataSource

        @Binds
        fun bindStreamLocalToDomainMapper(streamLocalToDomainMapperImpl: StreamLocalToDomainMapperImpl): StreamLocalToDomainMapper

        @Binds
        fun bindStreamRemoteToLocalMapper(streamLocalToDomainMapperImpl: StreamRemoteToLocalMapperImpl): StreamRemoteToLocalMapper

        @Binds
        fun bindTopicRemoteToLocalMapper(topicRemoteToLocalMapperImpl: TopicRemoteToLocalMapperImpl): TopicRemoteToLocalMapper

        @Binds
        fun bindTopicRemoteToDomainMapper(topicRemoteToDomainMapperImpl: TopicRemoteToDomainMapperImpl): TopicRemoteToDomainMapper
    }
}