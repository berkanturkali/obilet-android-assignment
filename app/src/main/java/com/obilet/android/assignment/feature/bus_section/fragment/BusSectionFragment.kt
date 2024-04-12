package com.obilet.android.assignment.feature.bus_section.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.BusSectionDay
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.databinding.FragmentBusSectionBinding
import com.obilet.android.assignment.feature.base.BaseFragment
import com.obilet.android.assignment.feature.bus_section.viewmodel.BusSectionFragmentViewModel
import com.obilet.android.assignment.feature.search.viewmodel.SearchFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusSectionFragment :
    BaseFragment<FragmentBusSectionBinding>(FragmentBusSectionBinding::inflate) {

    private val searchFragmentViewModel by viewModels<SearchFragmentViewModel>(ownerProducer = { requireParentFragment() })

    private val viewModel by viewModels<BusSectionFragmentViewModel>()

    private var currentRotation = 0f

    private val rotateAnimation by lazy {
        binding.switchDirectionsBtn.getSpringAnimationForTheView(
            DynamicAnimation.ROTATION,
            SpringForce.STIFFNESS_LOW
        )
    }

    private val slideInBottom by lazy {
        binding.dateTv.getSpringAnimationForTheView(DynamicAnimation.TRANSLATION_Y)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rotateAnimation.setStartValue(0f)
        subscribeObservers()
        setClickListeners()
    }

    private fun subscribeObservers() {
        searchFragmentViewModel.originAndDestinationPair.observe(viewLifecycleOwner) {
            val (origin, destination) = it
            setOriginAndDestination(origin!!, destination!!)
        }
        viewModel.selectedDay.observe(viewLifecycleOwner) { day ->
            when (day!!) {
                BusSectionDay.TODAY -> {
                    setTomorrowButtonColors(
                        textColor = R.color.button_color,
                        backgroundColor = R.color.on_primary
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
                        textColor = R.color.button_color,
                        backgroundColor = R.color.on_primary
                    )
                    setDateText(viewModel.getTodayOrderTomorrowDate(true))
                }
            }
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
            val (origin, destination) = searchFragmentViewModel.originAndDestinationPair.value!!
            searchFragmentViewModel.setOriginAndDestination(
                originAndDestinationPair = searchFragmentViewModel.originAndDestinationPair.value!!.copy(
                    first = destination,
                    origin
                )
            )
        }
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
            binding.tomorrowBtn,
            textColor = textColor,
            backgroundColor = backgroundColor
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
}