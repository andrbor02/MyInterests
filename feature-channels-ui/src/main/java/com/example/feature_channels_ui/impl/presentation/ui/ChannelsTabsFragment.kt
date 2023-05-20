package com.example.feature_channels_ui.impl.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.feature_channels_ui.R
import com.example.feature_channels_ui.databinding.FragmentTabLayoutBinding
import com.example.feature_channels_ui.impl.di.ChannelsUiComponentHolder
import com.example.feature_channels_ui.impl.di.annotations.ChannelsNavigation
import com.example.feature_channels_ui.impl.presentation.navigation.ChannelsScreens
import com.example.feature_channels_ui.impl.presentation.ui.viewpager.PagerAdapter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class ChannelsTabsFragment : Fragment() {

    private val binding: FragmentTabLayoutBinding by lazyUnsafe {
        FragmentTabLayoutBinding.inflate(layoutInflater)
    }

    @ChannelsNavigation
    @Inject
    lateinit var cicerone: Cicerone<Router>
    private val navigatorHolder by lazy { cicerone.getNavigatorHolder() }
    private val router by lazy { cicerone.router }

    private val navigator: Navigator by lazyUnsafe {
        AppNavigator(
            requireActivity(),
            containerId = R.id.tab_layout_container,
            fragmentManager = parentFragmentManager
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChannelsUiComponentHolder.get().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = binding.root

        initListeners()
        initPager()

        return view
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onStop() {
        super.onStop()
        navigatorHolder.removeNavigator()
    }

    private fun initListeners() {
        with(binding) {
            fab.setOnClickListener {
                router.navigateTo(ChannelsScreens.CreateChannel())
            }
            searchBut.setOnClickListener {
                router.navigateTo(ChannelsScreens.SearchChannels())
            }
        }
    }


    private fun initPager() {
        val tabs: List<String> = listOf(
            getString(R.string.subscribed),
            getString(R.string.all_streams)
        )
        val pagerAdapter = PagerAdapter(childFragmentManager, lifecycle)
        binding.fragmentViewPager.adapter = pagerAdapter
        pagerAdapter.update(
            listOf(
                ChannelsFragment.newInstance(showSubscribedOnly = true),
                ChannelsFragment.newInstance()
            )
        )
        TabLayoutMediator(binding.tabLayout, binding.fragmentViewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }
}