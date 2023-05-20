package com.example.feature_authorization_ui.impl.presentation.ui

//class PeopleFragment : Fragment() {

//    private val binding: FragmentPeopleBinding by lazyUnsafe {
//        FragmentPeopleBinding.inflate(layoutInflater)
//    }
//
//    private val adapter: PeopleAdapter by lazy {
//        PeopleAdapter(object : OnUserClickListener {
//            override fun click(userId: Int) {
//                navigateToProfile(userId)
//                requireContext().hideKeyboard(binding.root)
//            }
//        })
//    }
//
//    @Inject
//    lateinit var globalRouter: RouterForPeople
//
//    @Inject
//    lateinit var authorizationViewModelFactory: AuthorizationViewModelFactory
//
//    private val viewModel: AuthorizationViewModel by viewModels {
//        authorizationViewModelFactory
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        AuthorizationUiComponentHolder.get().inject(this)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        val view = binding.root
//        binding.peopleRecycler.adapter = adapter
//
//        if (savedInstanceState == null) {
//            viewModel.onEvent(Event.Ui.Init)
//        }
//
//        viewModel.stateFlow.collectWhenStarted(viewLifecycleOwner) { state ->
//            handleScreenState(state)
//        }
//        return view
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initListeners()
//    }
//
//    private fun initListeners() {
//        with(binding) {
//            tryAgainBut.setOnClickListener {
//                viewModel.onEvent(Event.Ui.ReloadListClick)
//            }
//
//            searchInput.addTextChangedListener { text ->
//                text?.let { query ->
//                    viewModel.onEvent(Event.Ui.Search(query.toString()))
//                }
//            }
//
//            searchBut.setOnClickListener {
//                showSearch()
//            }
//            backBut.setOnClickListener {
//                hideSearch()
//            }
//        }
//    }
//
//    private fun navigateToProfile(userId: Int) {
//        globalRouter.navigateToProfile(userId)
//    }
//
//    private fun handleScreenState(state: AuthorizationScreenState) {
//        when (state) {
//            is AuthorizationScreenState.ListError -> showError(state.errorMessage)
//            is AuthorizationScreenState.SearchError -> showSearchError(state.errorMessage)
//            is AuthorizationScreenState.Loading -> showLoading()
//            is AuthorizationScreenState.Success -> {
//                showList()
//                adapter.submitList(state.data)
//            }
//
//            is AuthorizationScreenState.Init -> {}
//        }
//    }
//
//    private fun showError(errorMessage: String) {
//        with(binding) {
//            shimmer.stopShimmer()
//            peopleRecycler.hide()
//            shimmer.hide()
//
//            errorContainer.show()
//            errorLabel.text = errorMessage
//        }
//    }
//
//    private fun showSearchError(errorMessage: String) {
//        with(binding) {
//            shimmer.stopShimmer()
//            peopleRecycler.hide()
//            shimmer.hide()
//
//            errorContainer.show()
//            tryAgainBut.hide()
//            errorLabel.text = errorMessage
//        }
//    }
//
//    private fun showLoading() {
//        with(binding) {
//            peopleRecycler.hide()
//            errorContainer.hide()
//
//            shimmer.startShimmer()
//            shimmer.show()
//        }
//    }
//
//    private fun showList() {
//        with(binding) {
//            shimmer.stopShimmer()
//            shimmer.hide()
//            errorContainer.hide()
//
//            peopleRecycler.show()
//        }
//    }
//
//    private fun showSearch() {
//        with(binding) {
//            usersLabel.hide()
//            searchBut.hide()
//
//            backBut.show()
//            searchInput.show()
//            requireContext().showKeyboard(searchInput)
//        }
//    }
//
//    private fun hideSearch() {
//        with(binding) {
//            searchInput.text.clear()
//            backBut.hide()
//            searchInput.hide()
//            requireContext().hideKeyboard(binding.root)
//
//            usersLabel.show()
//            searchBut.show()
//        }
//    }
//}