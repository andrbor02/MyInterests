package com.example.feature_chat.impl.di

import android.content.Context
import com.example.feature_chat.api.ChatComponent
import com.example.feature_chat.impl.data.datasource.local.MessagesLocalDataSource
import com.example.feature_chat.impl.data.datasource.local.impl.MessagesLocalDataSourceImpl
import com.example.feature_chat.impl.data.datasource.local.room.MessagesDao
import com.example.feature_chat.impl.data.datasource.local.room.RoomMessagesDatabase
import com.example.feature_chat.impl.data.datasource.remote.MessagesRemoteDataSource
import com.example.feature_chat.impl.data.datasource.remote.ReactionsRemoteDataSource
import com.example.feature_chat.impl.data.datasource.remote.impl.MessagesRemoteDataSourceImpl
import com.example.feature_chat.impl.data.datasource.remote.impl.ReactionsRemoteDataSourceImpl
import com.example.feature_chat.impl.data.datasource.utils.messages.MessageLocalToDomainMapper
import com.example.feature_chat.impl.data.datasource.utils.messages.MessageLocalToDomainMapperImpl
import com.example.feature_chat.impl.data.datasource.utils.messages.MessageRemoteToDomainMapper
import com.example.feature_chat.impl.data.datasource.utils.messages.MessageRemoteToDomainMapperImpl
import com.example.feature_chat.impl.data.datasource.utils.messages.MessagesRemoteToLocalMapper
import com.example.feature_chat.impl.data.datasource.utils.messages.MessagesRemoteToLocalMapperImpl
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionLocalToDomainMapper
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionLocalToDomainMapperImpl
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionRemoteToDomainMapper
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionRemoteToDomainMapperImpl
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionRemoteToLocalMapper
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionRemoteToLocalMapperImpl
import com.example.feature_chat.impl.data.datasource.utils.reactions.SuccessfulOperationChecker
import com.example.feature_chat.impl.data.datasource.utils.reactions.SuccessfulOperationCheckerImpl
import com.example.feature_chat.impl.data.repository_impl.ChatRepositoryImpl
import com.example.feature_chat.impl.domain.repository.ChatRepository
import com.example.feature_chat.impl.domain.usecase.AddReactionUseCase
import com.example.feature_chat.impl.domain.usecase.GetMessagesFromStreamUseCase
import com.example.feature_chat.impl.domain.usecase.GetMessagesFromTopicUseCase
import com.example.feature_chat.impl.domain.usecase.RemoveReactionUseCase
import com.example.feature_chat.impl.domain.usecase.SendMessageUseCase
import com.example.feature_chat.impl.domain.usecase.impl.AddReactionUseCaseImpl
import com.example.feature_chat.impl.domain.usecase.impl.GetMessagesFromStreamUseCaseImpl
import com.example.feature_chat.impl.domain.usecase.impl.GetMessagesFromTopicUseCaseImpl
import com.example.feature_chat.impl.domain.usecase.impl.RemoveReactionUseCaseImpl
import com.example.feature_chat.impl.domain.usecase.impl.SendMessageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ChatModule.Bindings::class]
)
internal class ChatModule {

    @Provides
    fun provideDatabase(context: Context): RoomMessagesDatabase {
        return RoomMessagesDatabase.getDatabase(
            context = context
        )
    }

    @Provides
    fun provideMessagesDao(roomMessagesDatabase: RoomMessagesDatabase): MessagesDao {
        return roomMessagesDatabase.messagesDao()
    }

    @Module
    interface Bindings {

        @Binds
        fun bindChatComponent(chatComponentimpl: ChatComponentimpl): ChatComponent

        @Binds
        fun bindGetMessagesFromTopicUseCase(getMessagesFromTopicUseCaseImpl: GetMessagesFromTopicUseCaseImpl): GetMessagesFromTopicUseCase

        @Binds
        fun bindGetMessagesFromStreamUseCase(getMessagesFromStreamUseCaseImpl: GetMessagesFromStreamUseCaseImpl): GetMessagesFromStreamUseCase

        @Binds
        fun bindSendMessageUseCase(sendMessageUseCaseImpl: SendMessageUseCaseImpl): SendMessageUseCase

        @Binds
        fun bindAddReactionUseCase(addReactionUseCaseImpl: AddReactionUseCaseImpl): AddReactionUseCase

        @Binds
        fun bindRemoveReactionUseCase(removeReactionUseCaseImpl: RemoveReactionUseCaseImpl): RemoveReactionUseCase

        @Binds
        fun bindChatRepository(messagesRepositoryImpl: ChatRepositoryImpl): ChatRepository

        @Binds
        fun bindMessagesRemoteDataSource(messagesRemoteDataSourceImpl: MessagesRemoteDataSourceImpl): MessagesRemoteDataSource

        @Binds
        fun bindReactionsRemoteDataSource(reactionsRemoteDataSourceImpl: ReactionsRemoteDataSourceImpl): ReactionsRemoteDataSource

        @Binds
        fun bindMessagesLocalDataSource(messagesLocalDataSourceImpl: MessagesLocalDataSourceImpl): MessagesLocalDataSource

        @Binds
        fun bindMessageLocalToDomainMapper(messageLocalToDomainMapperImpl: MessageLocalToDomainMapperImpl): MessageLocalToDomainMapper

        @Binds
        fun bindMessageRemoteToDomainMapper(messageRemoteToDomainMapperImpl: MessageRemoteToDomainMapperImpl): MessageRemoteToDomainMapper

        @Binds
        fun bindMessagesRemoteToLocalMapper(messagesRemoteToLocalMapperImpl: MessagesRemoteToLocalMapperImpl): MessagesRemoteToLocalMapper

        @Binds
        fun bindReactionLocalToDomainMapper(reactionLocalToDomainMapperImpl: ReactionLocalToDomainMapperImpl): ReactionLocalToDomainMapper

        @Binds
        fun bindReactionRemoteToDomainMapper(reactionRemoteToDomainMapperImpl: ReactionRemoteToDomainMapperImpl): ReactionRemoteToDomainMapper

        @Binds
        fun bindReactionRemoteToLocalMapper(reactionDataRemoteToDataLocalMapperImpl: ReactionRemoteToLocalMapperImpl): ReactionRemoteToLocalMapper

        @Binds
        fun bindSuccessfulOperationChecker(successfulOperationCheckerImpl: SuccessfulOperationCheckerImpl): SuccessfulOperationChecker
    }
}