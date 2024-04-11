package com.obilet.android.assignment.feature.flight_section.fragment

import android.os.Bundle
import android.view.View
import com.obilet.android.assignment.databinding.FragmentFlightSectionBinding
import com.obilet.android.assignment.feature.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightSectionFragment :
    BaseFragment<FragmentFlightSectionBinding>(FragmentFlightSectionBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}