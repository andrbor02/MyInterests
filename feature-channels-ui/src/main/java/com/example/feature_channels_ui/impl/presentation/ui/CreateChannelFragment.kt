package com.example.feature_channels_ui.impl.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.core_ui.R
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.core_utils_android.extensions.disable
import com.example.core_utils_android.extensions.enable
import com.example.core_utils_android.extensions.hide
import com.example.core_utils_android.extensions.show
import com.example.feature_channels_ui.databinding.FragmentCreateChannelBinding
import com.example.feature_channels_ui.impl.di.ChannelsUiComponentHolder
import com.example.feature_streams.impl.domain.model.StreamInfo
import com.example.feature_streams.impl.domain.usecase.CreateStreamUseCase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CreateChannelFragment : Fragment() {
    private val binding: FragmentCreateChannelBinding by lazyUnsafe {
        FragmentCreateChannelBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var createStreamUseCase: CreateStreamUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChannelsUiComponentHolder.get().inject(this)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = binding.root

        initListeners()

        return view
    }

    private fun initListeners() {
        with(binding) {
            createBtn.setOnClickListener {
                val name = channelName.text
                val description = channelDescription.text
                var hasPublicHistory = false
                publicHistoryCb.setOnCheckedChangeListener { _, isChecked ->
                    hasPublicHistory = isChecked
                }

                showLoading()
                lifecycleScope.launch {
                    val createdSuccessfully = createStreamUseCase(
                        StreamInfo(
                            name.toString(),
                            description.toString(),
                            hasPublicHistory
                        )
                    )
                    if (createdSuccessfully) {
                        navigateBack()
                    } else {
                        showError()
                    }
                }
            }

            backBut.setOnClickListener {
                navigateBack()
            }
        }
    }

    private fun showLoading() {
        with(binding) {
            createBtn.disable()
            channelName.disable()
            channelDescription.disable()
            publicHistoryCb.disable()

            progressBar.show()
        }
    }

    private fun navigateBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun showError() {
        with(binding) {
            createBtn.enable()
            channelName.enable()
            channelDescription.enable()
            publicHistoryCb.enable()
            progressBar.hide()

            Snackbar.make(root, CREATE_ERROR, Snackbar.LENGTH_LONG).apply {
                view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.error))
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }.show()
        }
    }

    companion object {
        private const val CREATE_ERROR = "Can't create channel"
    }
}
