package com.obilet.android.assignment.feature.flight_section.fragment

import android.os.Bundle
import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.databinding.FragmentFlightSectionBinding
import com.obilet.android.assignment.feature.base.BaseFragment
import com.obilet.android.assignment.feature.flight_section.viewmodel.FlightSectionFragmentViewModel
import com.obilet.android.assignment.feature.location.args.LocationsFragmentArgs
import com.obilet.android.assignment.feature.location.fragment.LocationsFragment
import com.obilet.android.assignment.feature.location.model.LocationDirection
import com.obilet.android.assignment.feature.search.TabItemFragment
import com.obilet.android.assignment.feature.search.fragment.SearchFragmentDirections
import com.obilet.android.assignment.feature.search.viewmodel.SearchFragmentViewModel
import com.obilet.android.assignment.utils.getNavigationResult
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class FlightSectionFragment :
    BaseFragment<FragmentFlightSectionBinding>(FragmentFlightSectionBinding::inflate) {

    companion object {
        private const val ENGLISH_LANG = "en"
    }

    private val searchFragmentViewModel by viewModels<SearchFragmentViewModel>(ownerProducer = { requireParentFragment() })

    private val viewModel by viewModels<FlightSectionFragmentViewModel>()

    private lateinit var rotateAnimation: SpringAnimation

    private lateinit var slideInLeftOrigin: SpringAnimation

    private lateinit var slideInLeftDestination: SpringAnimation

    private lateinit var addOrRemoveReturnDateButtonRotateAnimation: SpringAnimation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnimations()
        subscribeObservers()
        setClickListeners()
    }

    private fun initAnimations() {
        rotateAnimation = binding.switchDirectionsBtn.getSpringAnimationForTheView(
            DynamicAnimation.ROTATION, SpringForce.STIFFNESS_LOW
        )

        slideInLeftOrigin = binding.originTv.getSpringAnimationForTheView(
            DynamicAnimation.TRANSLATION_X,
            SpringForce.STIFFNESS_MEDIUM,
            SpringForce.DAMPING_RATIO_NO_BOUNCY
        )

        slideInLeftDestination = binding.destinationTv.getSpringAnimationForTheView(
            DynamicAnimation.TRANSLATION_X,
            SpringForce.STIFFNESS_MEDIUM,
            SpringForce.DAMPING_RATIO_NO_BOUNCY
        )

        addOrRemoveReturnDateButtonRotateAnimation =
            binding.addOrRemoveBtn.getSpringAnimationForTheView(
                DynamicAnimation.ROTATION,
                SpringForce.STIFFNESS_MEDIUM,
                SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            )
    }

    private fun subscribeObservers() {
        searchFragmentViewModel.originAndDestinationPair.observe(viewLifecycleOwner) {
            if (viewModel.originAndDestinationPair.value?.first != null) return@observe
            viewModel.setOriginAndDestination(it)
        }

        viewModel.departureDate.observe(viewLifecycleOwner) { date ->
            val formattedDate = viewModel.formatDepartureDate(date)
            val (day, month, dayOfTheWeek) = formattedDate
            setDepartureDate(day, month, dayOfTheWeek)
        }

        viewModel.returnDate.observe(viewLifecycleOwner) { returnDate ->
            setVisibilityOfReturnDate(returnDate != null)
        }

        viewModel.passengerCount.observe(viewLifecycleOwner) { passengers ->
            val passengerText = passengers.joinToString(", ") {
                val (title, count) = it
                val modifiedTitle = viewModel.removeParenthesesFromTheTitle(getString(title))
                if (Locale.getDefault().language == ENGLISH_LANG) {
                    "$count ${
                        viewModel.makeTheTitlePluralIfTheCountIsGreaterThanOne(
                            count,
                            modifiedTitle
                        )
                    }"
                } else {
                    "$count $modifiedTitle"
                }
            }
            binding.passengerTv.text = passengerText

        }

        viewModel.originAndDestinationPair.observe(viewLifecycleOwner) {
            val (origin, destination) = it
            setOriginAndDestination(origin!!, destination!!)
        }

        getNavigationResult<Bundle>(
            R.id.searchFragment,
            LocationsFragment.FLIGHT_SECTION_SELECTED_LOCATION_KEY
        ) {
            val location = it.getParcelable<BusLocation>(LocationsFragment.SELECTED_LOCATION)
            val direction = it.get(LocationsFragment.DIRECTION) as LocationDirection
            onOriginDestinationSelected(direction, location!!)
        }
    }

    private fun setDepartureDate(day: String, month: String, dayOfTheWeek: String) {
        binding.apply {
            dayOfMonthTv.text = day
            monthAndDayTv.text =
                getString(R.string.flight_section_month_and_day, month, dayOfTheWeek)
        }
    }

    private fun setOriginAndDestination(origin: BusLocation, destination: BusLocation) {
        startSlideInAnimationsForOriginAndDestination()
        binding.apply {
            originTv.text = origin.name
            destinationTv.text = destination.name
        }
    }

    private fun setVisibilityOfReturnDate(show: Boolean) {
        val visibilityOfReturnDate = if (show) View.VISIBLE else View.GONE
        val visibilityOfAddReturnTv = if (show) View.GONE else View.VISIBLE
        binding.addReturnTv.visibility = visibilityOfAddReturnTv
        binding.returnDayOfMonthTv.visibility = visibilityOfReturnDate
        binding.returnMonthAndDayTv.visibility = visibilityOfReturnDate
    }

    private fun startSlideInAnimationsForOriginAndDestination() {
        slideInLeftOrigin.setStartValue(-binding.originTv.width.toFloat())
        slideInLeftOrigin.animateToFinalPosition(0f)
        slideInLeftDestination.setStartValue(-binding.destinationTv.width.toFloat())
        slideInLeftDestination.animateToFinalPosition(0f)
        slideInLeftOrigin.start()
        slideInLeftDestination.start()
    }


    private fun setClickListeners() {
        binding.originTv.setOnClickListener {
            navigateToLocationsFragment(LocationDirection.ORIGIN)
        }

        binding.destinationTv.setOnClickListener {
            navigateToLocationsFragment(LocationDirection.DESTINATION)
        }

        binding.switchDirectionsBtn.setOnClickListener {
            startRotateAnimation()
            val (origin, destination) = viewModel.originAndDestinationPair.value!!
            viewModel.setOriginAndDestination(
                originAndDestinationPair = searchFragmentViewModel.originAndDestinationPair.value!!.copy(
                    first = destination, origin
                )
            )
        }

        binding.addOrRemoveBtn.setOnClickListener {
            startAnimationOfAddOrRemoveDateButton()
        }

        binding.addPassengerTv.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_flightSectionSelectPassengerDialog)
        }
    }

    private fun navigateToLocationsFragment(direction: LocationDirection) {
        val (origin, destination) = viewModel.originAndDestinationPair.value!!
        val action = SearchFragmentDirections.actionSearchFragmentToLocationsFragment(
            LocationsFragmentArgs(
                locationList = searchFragmentViewModel.busLocations.value?.data ?: emptyList(),
                direction = direction,
                selectedOrigin = origin!!,
                selectedDestination = destination!!,
                previousTabItemLabelId = TabItemFragment.FLIGHT_SECTION.label
            )
        )

        findNavController().navigate(action)
    }

    private fun startAnimationOfAddOrRemoveDateButton() {
        addOrRemoveReturnDateButtonRotateAnimation.setStartValue(
            viewModel.currentRotationOfAddOrRemoveDateButton
        )
        viewModel.currentRotationOfAddOrRemoveDateButton += 45
        addOrRemoveReturnDateButtonRotateAnimation.spring.finalPosition =
            viewModel.currentRotationOfAddOrRemoveDateButton
        addOrRemoveReturnDateButtonRotateAnimation.start()
        viewModel.currentRotationOfAddOrRemoveDateButton %= 90

    }

    private fun startRotateAnimation() {
        rotateAnimation.setStartValue(viewModel.currentRotation)
        viewModel.currentRotation += 180
        rotateAnimation.spring.finalPosition = viewModel.currentRotation
        viewModel.currentRotation %= 360
        rotateAnimation.start()
    }


    private fun View.getSpringAnimationForTheView(
        animation: FloatPropertyCompat<View>,
        stiffness: Float = SpringForce.STIFFNESS_MEDIUM,
        dampingRatio: Float? = null
    ): SpringAnimation {
        return SpringAnimation(this, animation).apply {
            spring = SpringForce()
            spring.stiffness = stiffness
            if (dampingRatio != null) {
                spring.dampingRatio = dampingRatio
            }

        }
    }

    fun onOriginDestinationSelected(direction: LocationDirection, location: BusLocation) {
        val (origin, destination) = viewModel.originAndDestinationPair.value!!
        when (direction) {
            LocationDirection.ORIGIN -> {
                viewModel.setOriginAndDestination(
                    viewModel.originAndDestinationPair.value!!.copy(
                        first = location, second = destination
                    )
                )
            }

            LocationDirection.DESTINATION -> {
                viewModel.setOriginAndDestination(
                    viewModel.originAndDestinationPair.value!!.copy(
                        first = origin, second = location
                    )
                )
            }
        }
    }

}


