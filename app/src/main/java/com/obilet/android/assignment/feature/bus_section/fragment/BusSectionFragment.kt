package com.obilet.android.assignment.feature.bus_section.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.BusSectionDay
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.databinding.FragmentBusSectionBinding
import com.obilet.android.assignment.feature.base.BaseFragment
import com.obilet.android.assignment.feature.bus_section.viewmodel.BusSectionFragmentViewModel
import com.obilet.android.assignment.feature.journeys.args.BusJourneysFragmentArgs
import com.obilet.android.assignment.feature.location.args.LocationsFragmentArgs
import com.obilet.android.assignment.feature.location.fragment.LocationsFragment
import com.obilet.android.assignment.feature.location.model.LocationDirection
import com.obilet.android.assignment.feature.search.TabItemFragment
import com.obilet.android.assignment.feature.search.fragment.SearchFragmentDirections
import com.obilet.android.assignment.feature.search.viewmodel.SearchFragmentViewModel
import com.obilet.android.assignment.utils.getNavigationResult
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class BusSectionFragment :
    BaseFragment<FragmentBusSectionBinding>(FragmentBusSectionBinding::inflate) {

    companion object {
        private const val DEPARTURE_DATE_PATTERN = "yyyy-MM-dd"
    }

    private val searchFragmentViewModel by viewModels<SearchFragmentViewModel>(ownerProducer = { requireParentFragment() })

    private val viewModel by viewModels<BusSectionFragmentViewModel>()

    private var currentRotation = 0f

    private lateinit var rotateAnimation: SpringAnimation

    private lateinit var slideInBottom: SpringAnimation

    private lateinit var slideInLeftOrigin: SpringAnimation

    private lateinit var slideInLeftDestination: SpringAnimation
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnimations()
        subscribeObservers()
        setClickListeners()
    }

    private fun initAnimations() {
        slideInBottom = binding.dateTv.getSpringAnimationForTheView(DynamicAnimation.TRANSLATION_Y)
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
        rotateAnimation = binding.switchDirectionsBtn.getSpringAnimationForTheView(
            DynamicAnimation.ROTATION, SpringForce.STIFFNESS_LOW
        )
    }

    private fun subscribeObservers() {
        searchFragmentViewModel.originAndDestinationPair.observe(viewLifecycleOwner) {
            if (viewModel.originAndDestinationPair.value?.first != null) return@observe
            viewModel.setOriginAndDestination(it)
        }
        viewModel.selectedDay.observe(viewLifecycleOwner) { day ->
            when (day!!) {
                BusSectionDay.TODAY -> {
                    setTomorrowButtonColors(
                        textColor = R.color.button_color, backgroundColor = R.color.on_primary
                    )
                    setTodayButtonColors(
                        textColor = R.color.button_text_color,
                        backgroundColor = R.color.button_color
                    )
                    setDateText(viewModel.getTodayOrderTomorrowDate(false))
                }

                BusSectionDay.TOMORROW -> {
                    setTomorrowButtonColors(
                        textColor = R.color.button_text_color,
                        backgroundColor = R.color.button_color
                    )
                    setTodayButtonColors(
                        textColor = R.color.button_color, backgroundColor = R.color.on_primary
                    )
                    setDateText(viewModel.getTodayOrderTomorrowDate(true))
                }

                BusSectionDay.OTHER -> {
                    setTomorrowButtonColors(
                        textColor = R.color.button_color,
                        backgroundColor = R.color.on_primary
                    )
                    setTodayButtonColors(
                        textColor = R.color.button_color, backgroundColor = R.color.on_primary
                    )
                    setDateText(viewModel.formatTheDateWithTheGivenPattern(viewModel.selectedDate))
                }
            }
        }

        viewModel.originAndDestinationPair.observe(viewLifecycleOwner) {
            val (origin, destination) = it
            setOriginAndDestination(origin!!, destination!!)
        }

        getNavigationResult<Bundle>(
            R.id.searchFragment,
            LocationsFragment.BUS_SECTION_SELECTED_LOCATION_KEY
        ) {
            val location = it.getParcelable<BusLocation>(LocationsFragment.SELECTED_LOCATION)
            val direction = it.get(LocationsFragment.DIRECTION) as LocationDirection
            onOriginDestinationSelected(direction, location!!)
        }
    }

    private fun setDateText(date: String) {
        startDateAnimation()
        binding.dateTv.text = date
    }

    private fun startDateAnimation() {
        slideInBottom.setStartValue(+binding.dateTv.height.toFloat())
        slideInBottom.animateToFinalPosition(0f)
        slideInBottom.start()
    }

    private fun setClickListeners() {
        binding.todayBtn.setOnClickListener {
            viewModel.setSelectedDay(BusSectionDay.TODAY)
        }

        binding.tomorrowBtn.setOnClickListener {
            viewModel.setSelectedDay(BusSectionDay.TOMORROW)
        }

        binding.switchDirectionsBtn.setOnClickListener {
            startRotateAnimation()
            val (origin, destination) = viewModel.originAndDestinationPair.value!!
            viewModel.setOriginAndDestination(
                originAndDestinationPair = searchFragmentViewModel.originAndDestinationPair.value!!.copy(
                    first = destination, second = origin
                )
            )
        }

        binding.originTv.setOnClickListener {
            navigateToLocationsFragment(LocationDirection.ORIGIN)
        }

        binding.destinationTv.setOnClickListener {
            navigateToLocationsFragment(LocationDirection.DESTINATION)
        }

        binding.dateTv.setOnClickListener {
            showDatePickerWithTheSelectedDate(viewModel.selectedDate)
        }

        binding.findTicketBtn.setOnClickListener {
            navigateToBusJourneysFragment()
        }
    }

    private fun navigateToBusJourneysFragment() {
        viewModel.originAndDestinationPair.value?.let {
            val (origin, destination) = it
            if (origin?.id != null && destination?.id != null) {
                val action = SearchFragmentDirections.actionSearchFragmentToBusJourneysFragment(
                    BusJourneysFragmentArgs(
                        originId = origin.id!!,
                        destinationId = destination.id!!,
                        departureDate = viewModel.formatTheDateWithTheGivenPattern(
                            viewModel.selectedDate,
                            DEPARTURE_DATE_PATTERN
                        ),
                        originName = origin.name,
                        destinationName = destination.name
                    )
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun showDatePickerWithTheSelectedDate(selectedDate: Date) {
        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(selectedDate.time)
            .setCalendarConstraints(constraintsBuilder.build())
            .setTheme(R.style.Theme_OBilet_DatePickerStyle)
            .build()
        datePicker.show(childFragmentManager, "tag")
        datePicker.addOnPositiveButtonClickListener { dateInMillis ->
            // TODO: check if the selected date is today or tomorrow
            // TODO: set the button colors by the selected date
            viewModel.selectedDate = Date(dateInMillis)
            viewModel.setSelectedDay(BusSectionDay.OTHER)
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
                previousTabItemLabelId = TabItemFragment.BUS_SECTION.label
            )
        )

        findNavController().navigate(action)
    }


    private fun startRotateAnimation() {
        rotateAnimation.setStartValue(currentRotation)
        currentRotation += 180
        rotateAnimation.spring.finalPosition = currentRotation
        currentRotation %= 360
        rotateAnimation.start()
    }

    private fun setTodayButtonColors(textColor: Int, backgroundColor: Int) {
        setButtonColors(binding.todayBtn, textColor = textColor, backgroundColor = backgroundColor)
    }

    private fun setTomorrowButtonColors(textColor: Int, backgroundColor: Int) {
        setButtonColors(
            binding.tomorrowBtn, textColor = textColor, backgroundColor = backgroundColor
        )
    }

    private fun setButtonColors(button: MaterialButton, textColor: Int, backgroundColor: Int) {
        button.setBackgroundColor(ContextCompat.getColor(requireContext(), backgroundColor))
        button.setTextColor(ContextCompat.getColor(requireContext(), textColor))
    }

    private fun setOriginAndDestination(origin: BusLocation, destination: BusLocation) {
        startSlideInAnimationsForOriginAndDestination()
        binding.apply {
            originTv.text = origin.name
            destinationTv.text = destination.name
        }
    }

    private fun startSlideInAnimationsForOriginAndDestination() {
        slideInLeftOrigin.setStartValue(-binding.originTv.width.toFloat())
        slideInLeftOrigin.animateToFinalPosition(0f)
        slideInLeftDestination.setStartValue(-binding.destinationTv.width.toFloat())
        slideInLeftDestination.animateToFinalPosition(0f)
        slideInLeftOrigin.start()
        slideInLeftDestination.start()
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

    private fun onOriginDestinationSelected(direction: LocationDirection, location: BusLocation) {
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