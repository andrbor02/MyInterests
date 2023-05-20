package com.example.feature_channels_ui.impl.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.core_navigation.impl.routers.RouterForChannels
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.core_utils_android.extensions.collectWhenStarted
import com.example.core_utils_android.extensions.hide
import com.example.core_utils_android.extensions.hideKeyboard
import com.example.core_utils_android.extensions.show
import com.example.core_utils_android.extensions.showKeyboard
import com.example.feature_channels_ui.databinding.FragmentSearchChannelsBinding
import com.example.feature_channels_ui.impl.di.ChannelsUiComponentHolder
import com.example.feature_channels_ui.impl.presentation.mvi.Event
import com.example.feature_channels_ui.impl.presentation.recycler.ChannelAdapter
import com.example.feature_channels_ui.impl.presentation.stateholders.OnStreamClickListener
import com.example.feature_channels_ui.impl.presentation.stateholders.all.ChannelsViewModel
import com.example.feature_channels_ui.impl.presentation.stateholders.all.ChannelsViewModelFactory
import com.example.homework_2.feature_channels.presentation.ChannelsScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SearchChannelsFragment : Fragment() {

    private val binding: FragmentSearchChannelsBinding by lazyUnsafe {
        FragmentSearchChannelsBinding.inflate(layoutInflater)
    }

    private val adapter: ChannelAdapter by lazy {
        ChannelAdapter(
            onStreamClickListener = object : OnStreamClickListener.Simple {
                override fun clickOnStream(streamId: Int, streamName: String) {
                    router.navigateToStream(streamId, streamName)
                }
            }
        )
    }

    @Inject
    lateinit var router: RouterForChannels

    @Inject
    lateinit var channelsViewModelFactory: ChannelsViewModelFactory
    private val viewModel: ChannelsViewModel by activityViewModels {
        channelsViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChannelsUiComponentHolder.get().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = binding.root
        binding.channelsNested.channelsRecycler.adapter = adapter

        initListeners()
        initSearch()

        viewModel.stateFlow.collectWhenStarted(viewLifecycleOwner) { state ->
            handleScreenState(state)
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        requireContext().showKeyboard(binding.searchInput)
    }

    private fun initListeners() {
        with(binding) {
            backBut.setOnClickListener {
                requireContext().hideKeyboard(binding.root)
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun initSearch() {
        binding.searchInput.addTextChangedListener { text ->
            lifecycleScope.launch {
                text?.let { query ->
                    viewModel.onEvent(Event.Ui.Search(query.toString()))
                }
            }
        }
    }

    private fun handleScreenState(state: ChannelsScreenState) {
        when (state) {
            is ChannelsScreenState.SearchError -> showSearchError(state.errorMessage)
            is ChannelsScreenState.Loading -> showLoading()
            is ChannelsScreenState.Success -> {
                showList()
                adapter.submitList(state.data)
            }

            is ChannelsScreenState.ListError -> {}
            is ChannelsScreenState.Init -> {}
        }
    }

    private fun showSearchError(errorMessage: String) {
        with(binding.channelsNested) {
            shimmer.stopShimmer()
            channelsRecycler.hide()
            shimmer.hide()

            errorContainer.show()
            tryAgainBut.hide()
            errorLabel.text = errorMessage
        }
    }

    private fun showLoading() {
        with(binding.channelsNested) {
            channelsRecycler.hide()
            errorContainer.hide()

            shimmer.startShimmer()
            shimmer.show()
        }
    }

    private fun showList() {
        with(binding.channelsNested) {
            shimmer.stopShimmer()
            shimmer.hide()
            errorContainer.hide()

            channelsRecycler.show()
        }
    }
}