package com.example.feature_people_ui.impl.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.core_navigation.impl.routers.RouterForPeople
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.core_utils_android.extensions.collectWhenStarted
import com.example.core_utils_android.extensions.hide
import com.example.core_utils_android.extensions.hideKeyboard
import com.example.core_utils_android.extensions.show
import com.example.core_utils_android.extensions.showKeyboard
import com.example.feature_people_ui.databinding.FragmentPeopleBinding
import com.example.feature_people_ui.impl.di.PeopleUiComponentHolder
import com.example.feature_people_ui.impl.presentation.mvi.Event
import com.example.feature_people_ui.impl.presentation.mvi.PeopleScreenState
import com.example.feature_people_ui.impl.presentation.recycler.OnUserClickListener
import com.example.feature_people_ui.impl.presentation.recycler.PeopleAdapter
import com.example.feature_people_ui.impl.presentation.stateholders.PeopleViewModel
import com.example.feature_people_ui.impl.presentation.stateholders.PeopleViewModelFactory
import javax.inject.Inject

class PeopleFragment : Fragment() {

    private val binding: FragmentPeopleBinding by lazyUnsafe {
        FragmentPeopleBinding.inflate(layoutInflater)
    }

    private val adapter: PeopleAdapter by lazy {
        PeopleAdapter(object : OnUserClickListener {
            override fun click(userId: Int) {
                navigateToProfile(userId)
                requireContext().hideKeyboard(binding.root)
            }
        })
    }

    @Inject
    lateinit var globalRouter: RouterForPeople

    @Inject
    lateinit var peopleViewModelFactory: PeopleViewModelFactory

    private val viewModel: PeopleViewModel by viewModels {
        peopleViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PeopleUiComponentHolder.get().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = binding.root
        binding.peopleRecycler.adapter = adapter

        if (savedInstanceState == null) {
            viewModel.onEvent(Event.Ui.Init)
        }

        viewModel.stateFlow.collectWhenStarted(viewLifecycleOwner) { state ->
            handleScreenState(state)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            tryAgainBut.setOnClickListener {
                viewModel.onEvent(Event.Ui.ReloadListClick)
            }

            searchInput.addTextChangedListener { text ->
                text?.let { query ->
                    viewModel.onEvent(Event.Ui.Search(query.toString()))
                }
            }

            searchBut.setOnClickListener {
                showSearch()
            }
            backBut.setOnClickListener {
                hideSearch()
            }
        }
    }

    private fun navigateToProfile(userId: Int) {
        globalRouter.navigateToProfile(userId)
    }

    private fun handleScreenState(state: PeopleScreenState) {
        when (state) {
            is PeopleScreenState.ListError -> showError(state.errorMessage)
            is PeopleScreenState.SearchError -> showSearchError(state.errorMessage)
            is PeopleScreenState.Loading -> showLoading()
            is PeopleScreenState.Success -> {
                showList()
                adapter.submitList(state.data)
            }

            is PeopleScreenState.Init -> {}
        }
    }

    private fun showError(errorMessage: String) {
        with(binding) {
            shimmer.stopShimmer()
            peopleRecycler.hide()
            shimmer.hide()

            errorContainer.show()
            errorLabel.text = errorMessage
        }
    }

    private fun showSearchError(errorMessage: String) {
        with(binding) {
            shimmer.stopShimmer()
            peopleRecycler.hide()
            shimmer.hide()

            errorContainer.show()
            tryAgainBut.hide()
            errorLabel.text = errorMessage
        }
    }

    private fun showLoading() {
        with(binding) {
            peopleRecycler.hide()
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

            peopleRecycler.show()
        }
    }

    private fun showSearch() {
        with(binding) {
            usersLabel.hide()
            searchBut.hide()

            backBut.show()
            searchInput.show()
            requireContext().showKeyboard(searchInput)
        }
    }

    private fun hideSearch() {
        with(binding) {
            searchInput.text.clear()
            backBut.hide()
            searchInput.hide()
            requireContext().hideKeyboard(binding.root)

            usersLabel.show()
            searchBut.show()
        }
    }
}