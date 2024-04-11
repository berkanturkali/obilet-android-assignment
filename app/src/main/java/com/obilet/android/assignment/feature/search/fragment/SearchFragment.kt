package com.obilet.android.assignment.feature.search.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.obilet.android.assignment.databinding.FragmentSearchBinding
import com.obilet.android.assignment.feature.base.BaseFragment
import com.obilet.android.assignment.feature.search.TabItemFragment
import com.obilet.android.assignment.feature.search.adapter.SearchFragmentPagerAdapter
import com.obilet.android.assignment.feature.search.viewmodel.SearchFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private lateinit var tabLayoutMediator: TabLayoutMediator

    private val tabItems = TabItemFragment.values()

    private val viewModel by viewModels<SearchFragmentViewModel>()

    private val pagerAdapter by lazy {
        SearchFragmentPagerAdapter(
            fragments = tabItems.map { it.fragment },
            fragmentManager = childFragmentManager,
            lifecycle = lifecycle
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()
        subscribeObservers()
    }

    private fun initTabLayout() {
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.isUserInputEnabled = true
        tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = getString(tabItems[position].label)
                tab.icon = ContextCompat.getDrawable(requireContext(), tabItems[position].icon)
            }
        tabLayoutMediator.attach()
    }

    private fun subscribeObservers() {

    }

}