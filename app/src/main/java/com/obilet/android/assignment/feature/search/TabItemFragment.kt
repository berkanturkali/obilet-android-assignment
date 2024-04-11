package com.obilet.android.assignment.feature.search

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.obilet.android.assignment.R
import com.obilet.android.assignment.feature.bus_section.fragment.BusSectionFragment
import com.obilet.android.assignment.feature.flight_section.fragment.FlightSectionFragment

enum class TabItemFragment(
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    val fragment: Fragment,
) {
    BUS_SECTION(R.drawable.ic_bus, R.string.bus_tab_label, BusSectionFragment()),
    FLIGHT_SECTION(R.drawable.ic_plane, R.string.flight_tab_label, FlightSectionFragment()),

}
