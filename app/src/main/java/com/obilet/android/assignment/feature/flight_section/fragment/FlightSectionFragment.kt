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
import com.obilet.android.assignment.feature.search.viewmodel.SearchFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightSectionFragment :
    BaseFragment<FragmentFlightSectionBinding>(FragmentFlightSectionBinding::inflate) {

    private val searchFragmentViewModel by viewModels<SearchFragmentViewModel>(ownerProducer = { requireParentFragment() })

    private val viewModel by viewModels<FlightSectionFragmentViewModel>()

    private var currentRotation = 0f

    private var currentRotationOfAddOrRemoveDateButton = 0f

    private val rotateAnimation by lazy {
        binding.switchDirectionsBtn.getSpringAnimationForTheView(
            DynamicAnimation.ROTATION,
            SpringForce.STIFFNESS_LOW
        )
    }
    private val slideInLeftOrigin by lazy {
        binding.originTv.getSpringAnimationForTheView(
            DynamicAnimation.TRANSLATION_X,
            SpringForce.STIFFNESS_MEDIUM,
            SpringForce.DAMPING_RATIO_NO_BOUNCY
        )
    }

    private val slideInLeftDestination by lazy {
        binding.destinationTv.getSpringAnimationForTheView(
            DynamicAnimation.TRANSLATION_X,
            SpringForce.STIFFNESS_MEDIUM,
            SpringForce.DAMPING_RATIO_NO_BOUNCY
        )
    }

    private val addOrRemoveReturnDateButtonRotateAnimation by lazy {
        binding.addOrRemoveBtn.getSpringAnimationForTheView(
            DynamicAnimation.ROTATION,
            SpringForce.STIFFNESS_MEDIUM,
            SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        setClickListeners()
    }

    private fun subscribeObservers() {
        searchFragmentViewModel.originAndDestinationPair.observe(viewLifecycleOwner) {
            val (origin, destination) = it
            setOriginAndDestination(origin!!, destination!!)
        }

        viewModel.defaultDepartureDate.observe(viewLifecycleOwner) { date ->
            val (day, month, dayOfTheWeek) = date
            setDepartureDate(day, month, dayOfTheWeek)
        }

        viewModel.returnDate.observe(viewLifecycleOwner) { returnDate ->
            setVisibilityOfReturnDate(returnDate != null)
        }

        viewModel.passengerCount.observe(viewLifecycleOwner) { count ->
            binding.passengerCountTv.text =
                getString(R.string.flight_passenger_adult, count.toString())
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
        binding.switchDirectionsBtn.setOnClickListener {
            startRotateAnimation()
            val (origin, destination) = searchFragmentViewModel.originAndDestinationPair.value!!
            searchFragmentViewModel.setOriginAndDestination(
                originAndDestinationPair = searchFragmentViewModel.originAndDestinationPair.value!!.copy(
                    first = destination,
                    origin
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

    private fun startAnimationOfAddOrRemoveDateButton() {
        addOrRemoveReturnDateButtonRotateAnimation.setStartValue(
            currentRotationOfAddOrRemoveDateButton
        )
        currentRotationOfAddOrRemoveDateButton += 45
        currentRotationOfAddOrRemoveDateButton %= 90
        addOrRemoveReturnDateButtonRotateAnimation.spring.finalPosition =
            currentRotationOfAddOrRemoveDateButton

        addOrRemoveReturnDateButtonRotateAnimation.start()

    }

    private fun startRotateAnimation() {
        rotateAnimation.setStartValue(currentRotation)
        currentRotation += 180
        rotateAnimation.spring.finalPosition = currentRotation
        currentRotation %= 360
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

}