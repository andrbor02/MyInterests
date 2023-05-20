package com.example.feature_chat_ui.impl.presentation.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.core_utils.common_helpers.runCatchingNonCancellation
import com.example.core_utils_android.extensions.collectWhenStarted
import com.example.core_utils_android.extensions.hide
import com.example.core_utils_android.extensions.show
import com.example.feature_chat.impl.domain.model.SendMessageModel
import com.example.feature_chat.impl.domain.model.utils.ChatPath
import com.example.feature_chat_ui.databinding.FragmentChatBinding
import com.example.feature_chat_ui.impl.di.ChatUiComponentHolder
import com.example.feature_chat_ui.impl.presentation.components.reaction_sheet.OnReactionClickListener
import com.example.feature_chat_ui.impl.presentation.model.date.DateDelegate
import com.example.feature_chat_ui.impl.presentation.model.loading.LoadingDelegate
import com.example.feature_chat_ui.impl.presentation.model.message.MessageDelegate
import com.example.feature_chat_ui.impl.presentation.model.topic.TopicDelegate
import com.example.feature_chat_ui.impl.presentation.mvi.ChatScreenState
import com.example.feature_chat_ui.impl.presentation.mvi.Event
import com.example.feature_chat_ui.impl.presentation.recycler.delegates.MainAdapter
import com.example.feature_chat_ui.impl.presentation.stateholders.ChatViewModel
import com.example.feature_chat_ui.impl.presentation.stateholders.ChatViewModelFactory
import com.example.feature_streams.impl.domain.usecase.GetTopicsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatFragment : Fragment() {

    private val binding: FragmentChatBinding by lazyUnsafe {
        FragmentChatBinding.inflate(layoutInflater)
    }

    private val adapter: MainAdapter by lazy { MainAdapter() }

    @Inject
    lateinit var chatViewModelFactory: ChatViewModelFactory

    private val viewModel: ChatViewModel by viewModels {
        chatViewModelFactory
    }

    @Inject
    lateinit var getTopicsUseCase: GetTopicsUseCase

    private var streamId: Int = 0
    private var streamName: String = ""
    private var topicName: String = ""
    private var topics: List<String> = emptyList()
    private var isStreamChat: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChatUiComponentHolder.get().inject(this)

        arguments?.let { bundle ->
            streamId = bundle.getInt(STREAM_ID_KEY)
            streamName = bundle.getString(STREAM_NAME_KEY, "")
            topicName = bundle.getString(TOPIC_NAME_KEY, "")
        }
        isStreamChat = topicName.isEmpty()

        viewModel.onEvent(Event.Ui.Init)
        loadTopics()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = binding.root

        binding.streamLabel.text = streamName

        loadChat()
        initRecycler()
        initPaging()
        initListeners()

        viewModel.stateFlow.collectWhenStarted(viewLifecycleOwner) { state ->
            handleScreenState(state)
        }

        return view
    }

    private fun initListeners() {
        with(binding) {
            sendButton.setOnClickListener {
                if (isStreamChat && topics.isNotEmpty()) {
                    chooseWithMenu()
                } else {
                    val text = messageInput.text.toString()
                    messageInput.text.clear()
                    val message = SendMessageModel(
                        stream = streamName,
                        topic = topicName,
                        text = text
                    )
                    sendMessage(message)
                }
            }

            backBut.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

            tryAgainBut.setOnClickListener {
                loadChat()
            }
        }
    }

    private fun loadChat() {
        viewModel.onEvent(
            Event.Ui.LoadChat(
                showAllTopics = isStreamChat,
                ChatPath(
                    streamId = streamId,
                    topicName = topicName,
                )
            )
        )
    }

    private fun reactionListener() = object : OnReactionClickListener {
        override fun addReaction(messageId: Int, emojiName: String) {
            viewModel.onEvent(
                Event.Ui.AddReaction(
                    ChatPath(
                        streamId = streamId,
                        topicName = topicName,
                    ),
                    messageId, emojiName
                )
            )
        }

        override fun removeReaction(messageId: Int, emojiName: String) {
            viewModel.onEvent(
                Event.Ui.RemoveReaction(
                    ChatPath(
                        streamId = streamId,
                        topicName = topicName,
                    ),
                    messageId, emojiName
                )
            )
        }
    }

    private fun initRecycler() {
        adapter.apply {
            addDelegate(
                MessageDelegate(
                    requireContext(),
                    reactionListener()
                )
            )
            addDelegate(DateDelegate())
            addDelegate(TopicDelegate())
            addDelegate(LoadingDelegate())
        }
        binding.chatRecycler.adapter = adapter
    }

    private fun chooseWithMenu() {
        val menu = PopupMenu(
            requireContext(),
            binding.sendButton,
            Gravity.CENTER
        ).apply {
            topics.forEach { name ->
                this.menu.add(name)
            }
            setOnMenuItemClickListener { item ->
                val text = binding.messageInput.text.toString()
                binding.messageInput.text.clear()

                val message = SendMessageModel(
                    stream = streamName,
                    topic = item.title.toString(),
                    text = text
                )
                sendMessage(message)
                true
            }
        }
        menu.show()
    }

    private fun sendMessage(message: SendMessageModel) {
        viewModel.onEvent(Event.Ui.SendMessage(message))
    }

    private fun initPaging() {
        binding.chatRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val threshold = THRESHOLD
                if (firstVisibleItem <= threshold) {
                    if (viewModel.stateFlow.value is ChatScreenState.Success) {
                        viewModel.onEvent(
                            Event.Ui.LoadChat(
                                showAllTopics = isStreamChat,
                                chatPath = ChatPath(
                                    streamId,
                                    topicName
                                )
                            )
                        )
                    }
                }
            }
        })
    }

    private fun loadTopics() {
        lifecycleScope.launch {
            val topicsLoaded = runCatchingNonCancellation {
                getTopicsUseCase(streamId)
            }

            topicsLoaded.fold(
                onSuccess = { loadedTopics ->
                    topics = loadedTopics.map { it.name }
                },
                onFailure = {
                    showError()
                }
            )
        }
    }

    private fun handleScreenState(state: ChatScreenState) {
        when (state) {
            is ChatScreenState.Success -> {
                showList()
                adapter.submitList(state.data)
            }

            is ChatScreenState.Init -> {}
        }
    }

    private fun showList() {
        with(binding) {
            errorContainer.hide()
            chatRecycler.show()
        }
    }

    private fun showError() {
        with(binding) {
            chatRecycler.hide()
            errorContainer.show()
        }
    }

    companion object {
        private const val THRESHOLD = 5

        private const val STREAM_ID_KEY = "StreamIdKey"
        private const val STREAM_NAME_KEY = "StreamNameKey"
        private const val TOPIC_NAME_KEY = "TopicKey"

        @JvmStatic
        fun newInstance(
            streamId: Int,
            streamName: String,
            topicName: String?,
        ) = ChatFragment().apply {
            arguments = Bundle().apply {
                putInt(STREAM_ID_KEY, streamId)
                putString(STREAM_NAME_KEY, streamName)

                val isStreamChat = topicName != null
                if (isStreamChat) {
                    putString(TOPIC_NAME_KEY, topicName)
                }
            }
        }
    }
}