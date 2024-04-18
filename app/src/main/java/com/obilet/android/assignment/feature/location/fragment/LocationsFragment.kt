package com.obilet.android.assignment.feature.location.fragment

import android.animation.LayoutTransition
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.databinding.FragmentLocationsBinding
import com.obilet.android.assignment.feature.base.BaseFragment
import com.obilet.android.assignment.feature.location.adapter.LocationsAdapter
import com.obilet.android.assignment.feature.location.listener.LocationItemClickListener
import com.obilet.android.assignment.feature.location.listener.OnLocationItemSeeOnTheMapClickListener
import com.obilet.android.assignment.feature.location.model.LocationDirection
import com.obilet.android.assignment.feature.location.viewmodel.LocationsFragmentViewModel
import com.obilet.android.assignment.utils.setNavigationResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding>(FragmentLocationsBinding::inflate),
    LocationItemClickListener, OnLocationItemSeeOnTheMapClickListener {

    private val viewModel by viewModels<LocationsFragmentViewModel>()

    companion object {
        const val SCROLL_TO_TOP_BUTTON_ANIMATION_DURATION = 300L
        const val CLEAR_TEXT_BUTTON_ANIMATION_DURATION = 200L
        const val CANCEL_TEXT_ANIMATION_DURATION = 200L
        const val BUS_SECTION_SELECTED_LOCATION_KEY = "busSectionSelectedLocationKey"
        const val FLIGHT_SECTION_SELECTED_LOCATION_KEY = " flightSectionSelectedLocationKey"
        const val SELECTED_LOCATION = "selectedLocation"
        const val DIRECTION = "direction"
    }

    private val adapter by lazy {
        LocationsAdapter()
    }
    private var cancelTvTransition = Slide(Gravity.END)

    private val scrollToTopButtonTransition = Slide(Gravity.BOTTOM)

    private val clearTextButtonTransition = Slide(Gravity.END)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        subscribeObservers()
        setClickListeners()
    }

    private fun setupUI() {
        setToolbarTitle()
        adapter.setDirection(viewModel.args.direction)
        adapter.setListener(this)
        adapter.setSeeOnTheMapClickListener(this)
        binding.locationsRv.adapter = adapter
        binding.locationsRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                viewModel.setVisibilityOfScrollToTopButton((binding.locationsRv.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() > 1)
            }
        })

        binding.searchEt.doOnTextChanged { text, _, _, _ ->
            animateClearTextButton(show = !text?.toString().isNullOrEmpty())
            text?.toString()?.let(viewModel::setSearchQuery)
        }

        binding.searchEt.setOnFocusChangeListener { _, hasFocus ->
            animateCancelTvVisibility(hasFocus)
        }

    }

    private fun animateClearTextButton(show: Boolean) {
        clearTextButtonTransition.duration = CLEAR_TEXT_BUTTON_ANIMATION_DURATION
        clearTextButtonTransition.addTarget(binding.clearQueryIv)

        TransitionManager.beginDelayedTransition(
            binding.searchBoxCv, clearTextButtonTransition
        )

        binding.clearQueryIv.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun animateCancelTvVisibility(visible: Boolean) {
        cancelTvTransition.apply {
            duration = CANCEL_TEXT_ANIMATION_DURATION
            addTarget(binding.cancelTv)
        }
        TransitionManager.beginDelayedTransition(
            binding.locationsRv, cancelTvTransition
        )
        binding.cancelTv.isVisible = visible
        (binding.searchBoxCv as ViewGroup).layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    private fun setToolbarTitle() {
        val title =
            if (viewModel.args.direction == LocationDirection.ORIGIN) getString(R.string.origin_label) else getString(
                R.string.destination_label
            )
        binding.titleTv.text = title
    }

    private fun subscribeObservers() {
        viewModel.locations.observe(viewLifecycleOwner) { locationList ->
            adapter.submitList(locationList)
        }

        viewModel.visibilityOfScrollToTopButton.observe(viewLifecycleOwner) { visible ->
            animateFabVisibility(visible)
        }
    }

    private fun setClickListeners() {
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.scrollToTopBtn.setOnClickListener {
            binding.locationsRv.scrollToPosition(0)
        }

        binding.cancelTv.setOnClickListener {
            animateCancelTvVisibility(false)
            binding.searchEt.clearFocus()
            hideKeyboard()
        }
        binding.clearQueryIv.setOnClickListener {
            animateClearTextButton(false)
            binding.searchEt.setText("")
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(binding.cancelTv.windowToken, 0)
    }

    override fun onLocationItemClick(direction: LocationDirection, busLocation: BusLocation) {
        if ((direction == LocationDirection.ORIGIN && viewModel.args.selectedDestination.id == busLocation.id) || (direction == LocationDirection.DESTINATION && viewModel.args.selectedOrigin.id == busLocation.id)) {
            showErrorDialogWithOkayButton(getString(R.string.origin_and_destination_can_not_be_the_same))
            return
        }

        val key = if (viewModel.args.previousTabItemLabelId == R.string.bus_tab_label) {
            BUS_SECTION_SELECTED_LOCATION_KEY
        } else {
            FLIGHT_SECTION_SELECTED_LOCATION_KEY
        }

        setNavigationResult(
            key, bundleOf(SELECTED_LOCATION to busLocation, DIRECTION to direction)
        )
        findNavController().navigateUp()
    }

    private fun animateFabVisibility(visible: Boolean) {
        scrollToTopButtonTransition.apply {
            duration = SCROLL_TO_TOP_BUTTON_ANIMATION_DURATION
            addTarget(binding.scrollToTopCv)
        }
        TransitionManager.beginDelayedTransition(
            binding.locationsRv, scrollToTopButtonTransition
        )
        binding.scrollToTopCv.isVisible = visible
    }

    override fun onSeeOnTheMapClicked(location: BusLocation) {
        if (location.latitude != null && location.longitude != null) {
            navigateToGoogleMapsWithTheCoordinates(
                long = location.longitude!!, lat = location.latitude!!
            )
        }
    }

    private fun navigateToGoogleMapsWithTheCoordinates(long: Double, lat: Double) {
        val intentUri = Uri.parse("geo:$lat,$long?z=11&q=$lat,$long")
        val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(requireActivity().packageManager)?.let {
            requireActivity().startActivity(mapIntent)
        }
    }
}