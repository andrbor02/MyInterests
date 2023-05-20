package com.example.feature_chat.impl.data.repositoty_impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.feature_chat.impl.data.datasource.local.MessagesLocalDataSource
import com.example.feature_chat.impl.data.datasource.remote.MessagesRemoteDataSource
import com.example.feature_chat.impl.data.datasource.remote.ReactionsRemoteDataSource
import com.example.feature_chat.impl.data.datasource.utils.messages.MessageLocalToDomainMapperImpl
import com.example.feature_chat.impl.data.datasource.utils.messages.MessageRemoteToDomainMapperImpl
import com.example.feature_chat.impl.data.datasource.utils.messages.MessagesRemoteToLocalMapperImpl
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionLocalToDomainMapperImpl
import com.example.feature_chat.impl.data.datasource.utils.reactions.ReactionRemoteToLocalMapperImpl
import com.example.feature_chat.impl.data.datasource.utils.reactions.SuccessfulOperationCheckerImpl
import com.example.feature_chat.impl.data.repository_impl.ChatRepositoryImpl
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat.impl.domain.repository.ChatRepository
import com.example.feature_chat.stub.MessagesLocalDataSourceStub
import com.example.feature_chat.stub.MessagesRemoteDataSourceStub
import com.example.feature_chat.stub.ReactionsRemoteDataSourceStub
import com.example.feature_chat.stub.ReactionsRemoteToDomainMapperStub
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ChatRepositoryImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addReaction() = runTest {
        val repository = createRepository()

        val updatedMessage = repository.addReaction(createChatPath(), 0, "octopus")

        val stubMessage = MessagesRemoteDataSourceStub.stubMessage
        assertEquals(updatedMessage, stubMessage)
    }

    private fun createRepository(
        messagesRemoteDataSource: MessagesRemoteDataSource = MessagesRemoteDataSourceStub(),
        reactionsRemoteDataSource: ReactionsRemoteDataSource = ReactionsRemoteDataSourceStub(),
        messagesLocalDataSource: MessagesLocalDataSource = MessagesLocalDataSourceStub(),
    ): ChatRepository {
        return ChatRepositoryImpl(
            messagesRemoteDataSource,
            reactionsRemoteDataSource,
            messagesLocalDataSource,
            MessageLocalToDomainMapperImpl(ReactionLocalToDomainMapperImpl()),
            MessageRemoteToDomainMapperImpl(ReactionsRemoteToDomainMapperStub()),
            MessagesRemoteToLocalMapperImpl(ReactionRemoteToLocalMapperImpl()),
            SuccessfulOperationCheckerImpl()
        )
    }

    private fun createChatPath(): ChatPath {
        return ChatPath(
            0, ""
        )
    }
}