package com.obilet.android.assignment.feature.bus_section.fragment

import android.os.Bundle
import android.view.View
import com.obilet.android.assignment.databinding.FragmentBusSectionBinding
import com.obilet.android.assignment.feature.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusSectionFragment :
    BaseFragment<FragmentBusSectionBinding>(FragmentBusSectionBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}