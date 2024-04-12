package com.obilet.android.assignment.feature.search.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.databinding.FragmentSearchBinding
import com.obilet.android.assignment.feature.base.BaseFragment
import com.obilet.android.assignment.feature.search.TabItemFragment
import com.obilet.android.assignment.feature.search.adapter.SearchFragmentPagerAdapter
import com.obilet.android.assignment.feature.search.component.SearchFragmentLoadingView
import com.obilet.android.assignment.feature.search.listener.RetryButtonClickListener
import com.obilet.android.assignment.feature.search.viewmodel.SearchFragmentViewModel
import com.obilet.android.assignment.utils.getThisOrDefaultErrorMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    RetryButtonClickListener {

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
        viewModel.busLocations.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {
                    showOrHideMainContent(show = true)
                    val message = resource.error?.getThisOrDefaultErrorMessage(requireContext())
                    message?.let {
                        showErrorDialog(it)
                    }
                }

                is Resource.Loading -> {
                    showOrHideMainContent(show = false)
                    binding.loadingView.setContent {
                        SearchFragmentLoadingView()
                    }
                }

                is Resource.Success -> {
                    if (!resource.data.isNullOrEmpty()) {
                        showOrHideMainContent(show = true)
                        val locations = resource.data
                        if (locations!!.size >= 2) {
                            val originAndDestinationPair =
                                viewModel.findDefaultLocationsByCityId(locations)
                            if (originAndDestinationPair.first != null && originAndDestinationPair.second != null) {
                                viewModel.setOriginAndDestination(originAndDestinationPair)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showOrHideMainContent(show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.GONE
        binding.viewPager.visibility = visibility
        binding.tabLayout.visibility = visibility
        binding.toolbar.visibility = visibility
        showOrHideLoadingView(!show)
    }

    private fun showOrHideLoadingView(show: Boolean) {
        binding.loadingView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onRetryButtonClick() {
        errorWithRetryButtonDialog.dismiss()
    }

}