package com.example.feature_channels_ui.impl.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.core_navigation.impl.routers.RouterForChannels
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.core_utils_android.extensions.collectWhenStarted
import com.example.core_utils_android.extensions.hide
import com.example.core_utils_android.extensions.show
import com.example.feature_channels_ui.databinding.FragmentChannelsBinding
import com.example.feature_channels_ui.impl.di.ChannelsUiComponentHolder
import com.example.feature_channels_ui.impl.presentation.mvi.Event
import com.example.feature_channels_ui.impl.presentation.recycler.ChannelAdapter
import com.example.feature_channels_ui.impl.presentation.stateholders.CommonChannelsViewModel
import com.example.feature_channels_ui.impl.presentation.stateholders.OnStreamClickListener
import com.example.feature_channels_ui.impl.presentation.stateholders.all.ChannelsViewModel
import com.example.feature_channels_ui.impl.presentation.stateholders.all.ChannelsViewModelFactory
import com.example.feature_channels_ui.impl.presentation.stateholders.subscribed.SubscribedChannelsViewModel
import com.example.feature_channels_ui.impl.presentation.stateholders.subscribed.SubscribedChannelsViewModelFactory
import com.example.homework_2.feature_channels.presentation.ChannelsScreenState
import javax.inject.Inject

class ChannelsFragment : Fragment(), OnStreamClickListener.Expandable.OnTopicClickListener {

    private val binding: FragmentChannelsBinding by lazyUnsafe {
        FragmentChannelsBinding.inflate(layoutInflater)
    }

    private val adapter: ChannelAdapter by lazy {
        ChannelAdapter(
            onStreamClickListener = object : OnStreamClickListener.Expandable(this) {
                override fun clickOnStream(streamId: Int, streamName: String) {
                    router.navigateToStream(streamId, streamName)
                }

                override fun clickOnExpandButton(streamId: Int) {
                    viewModel.onEvent(Event.Ui.LoadTopics(streamId))
                }
            },
        )
    }

    @Inject
    lateinit var router: RouterForChannels

    @Inject
    lateinit var channelsViewModelFactory: ChannelsViewModelFactory

    @Inject
    lateinit var subscribedChannelsViewModelFactory: SubscribedChannelsViewModelFactory

    private lateinit var viewModel: CommonChannelsViewModel

    private var streams = mapOf<Int, String>()
    private var showSubscribedOnly: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChannelsUiComponentHolder.get().inject(this)

        chooseViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = binding.root
        binding.channelsRecycler.adapter = adapter

        initListeners()
        if (savedInstanceState == null) {
            viewModel.onEvent(Event.Ui.Init)
        }
        viewModel.stateFlow.collectWhenStarted(viewLifecycleOwner) { state ->
            handleScreenState(state)
        }

        return view
    }

    override fun clickOnTopic(streamId: Int, topicName: String) {
        val streamName = streams.getOrDefault(streamId, "")
        router.navigateToTopic(streamId, streamName, topicName)
    }

    private fun initListeners() {
        with(binding) {
            tryAgainBut.setOnClickListener {
                viewModel.onEvent(Event.Ui.ReloadClick)
            }
        }
    }

    private fun handleScreenState(state: ChannelsScreenState) {
        when (state) {
            is ChannelsScreenState.ListError -> showError(state.errorMessage)
            is ChannelsScreenState.SearchError -> showSearchError(state.errorMessage)
            is ChannelsScreenState.Loading -> showLoading()
            is ChannelsScreenState.Success -> {
                streams = state.data.associate { it.id to it.name }

                showList()
                adapter.submitList(state.data)
            }

            is ChannelsScreenState.Init -> {}
        }
    }

    private fun showError(errorMessage: String) {
        with(binding) {
            shimmer.stopShimmer()
            channelsRecycler.hide()
            shimmer.hide()

            errorContainer.show()
            errorLabel.text = errorMessage
        }
    }

    private fun showSearchError(errorMessage: String) {
        with(binding) {
            shimmer.stopShimmer()
            channelsRecycler.hide()
            shimmer.hide()

            errorContainer.show()
            tryAgainBut.hide()
            errorLabel.text = errorMessage
        }
    }

    private fun showLoading() {
        with(binding) {
            channelsRecycler.hide()
            errorContainer.hide()

            shimmer.startShimmer()
            shimmer.show()
        }
    }

    private fun showList() {
        with(binding) {
            shimmer.stopShimmer()
            shimmer.hide()
            errorContainer.hide()

            channelsRecycler.show()
        }
    }

    private fun chooseViewModel() {
        arguments?.let { bundle ->
            showSubscribedOnly = bundle.getBoolean(SUBSCRIBED_ONLY)
        }
        viewModel = if (showSubscribedOnly) {
            ViewModelProvider(this, subscribedChannelsViewModelFactory)
                .get(SubscribedChannelsViewModel::class.java)
        } else {
            ViewModelProvider(this, channelsViewModelFactory)
                .get(ChannelsViewModel::class.java)
        }
    }

    companion object {
        private const val SUBSCRIBED_ONLY = "Subscribed only"

        @JvmStatic
        fun newInstance(showSubscribedOnly: Boolean = false) =
            ChannelsFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(SUBSCRIBED_ONLY, showSubscribedOnly)
                }
            }
    }
}