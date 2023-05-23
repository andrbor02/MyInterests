package com.example.feature_profile_ui.impl.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.core_ui.R
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.core_utils.mvi_helpers.ScreenState
import com.example.core_utils.myId
import com.example.core_utils_android.extensions.collectWhenStarted
import com.example.core_utils_android.extensions.hide
import com.example.core_utils_android.extensions.show
import com.example.feature_profile.impl.domain.model.ProfileModel
import com.example.feature_profile.impl.domain.model.StatusModel
import com.example.feature_profile_ui.databinding.FragmentProfileBinding
import com.example.feature_profile_ui.impl.di.ProfileUiComponentHolder
import com.example.feature_profile_ui.impl.presentation.mvi.Event
import com.example.feature_profile_ui.impl.presentation.mvi.ProfileScreenState
import com.example.feature_profile_ui.impl.presentation.stateholders.ProfileViewModel
import com.example.feature_profile_ui.impl.presentation.stateholders.ProfileViewModelFactory
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private val binding: FragmentProfileBinding by lazyUnsafe {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var profileViewModelFactory: ProfileViewModelFactory
    private val viewModel: ProfileViewModel by viewModels {
        profileViewModelFactory
    }

    private var profileId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProfileUiComponentHolder.get().inject(this)
        arguments?.let { bundle ->
            profileId = bundle.getInt(PROFILE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = binding.root

        if (savedInstanceState == null) {
            isMyProfile()
        }
        initListeners()

        viewModel.stateFlow.collectWhenStarted(viewLifecycleOwner) { state ->
            handleScreenState(state)
        }
        return view
    }

    private fun isMyProfile() {
        if (profileId == null) {
            binding.toolbar.hide()
            viewModel.onEvent(Event.Ui.LoadId(myId))
        } else {
            binding.logoutBtn.hide()
            viewModel.onEvent(Event.Ui.LoadId(profileId ?: DEFAULT_PROFILE_ID))
        }
    }

    private fun initListeners() {
        with(binding) {
            backBut.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            tryAgainBut.setOnClickListener {
                viewModel.onEvent(Event.Ui.LoadId(profileId ?: DEFAULT_PROFILE_ID))
            }
        }
    }

    private fun handleScreenState(state: ProfileScreenState) {
        when (state) {
            is ScreenState.Error -> showError(state.errorMessage)
            is ScreenState.Loading -> showLoading()
            is ScreenState.Success -> {
                fillProfile(state.data)
                showProfile()
            }

            is ScreenState.Init -> {}
        }
    }

    private fun fillProfile(profile: ProfileModel) {
        with(binding) {
            Glide
                .with(this.root)
                .load(profile.avatar)
                .placeholder(R.drawable.no_photography_48)
                .into(binding.avatar)

            name.text = profile.name

            when (profile.status) {
                StatusModel.ACTIVE -> setStatus(ONLINE, R.color.online_color)
                StatusModel.IDLE -> setStatus(WAITING, R.color.idle_color)
                StatusModel.OFFLINE -> setStatus(OFFLINE, R.color.offline_color)
            }
        }
    }

    private fun setStatus(statusLabel: String, @ColorRes color: Int) {
        with(binding) {
            status.text = statusLabel
            status.setTextColor(requireContext().getColor(color))
        }
    }

    private fun showError(errorMessage: String) {
        with(binding) {
            shimmer.stopShimmer()
            profileContainer.hide()
            shimmer.hide()

            errorContainer.show()
            errorLabel.text = errorMessage
        }
    }

    private fun showLoading() {
        with(binding) {
            profileContainer.hide()
            errorContainer.hide()

            shimmer.startShimmer()
            shimmer.show()
        }
    }

    private fun showProfile() {
        with(binding) {
            shimmer.stopShimmer()
            shimmer.hide()
            errorContainer.hide()

            profileContainer.show()
        }
    }

    companion object {
        private const val PROFILE_ID = "Profile id"
        private const val DEFAULT_PROFILE_ID = -1

        private const val ONLINE = "Online"
        private const val WAITING = "Waiting"
        private const val OFFLINE = "Offline"

        @JvmStatic
        fun newInstance(profileId: Int?): ProfileFragment {
            val fragment = ProfileFragment()
            if (profileId != null) {
                fragment.arguments = bundleOf(PROFILE_ID to profileId)
            }
            return fragment
        }
    }
}