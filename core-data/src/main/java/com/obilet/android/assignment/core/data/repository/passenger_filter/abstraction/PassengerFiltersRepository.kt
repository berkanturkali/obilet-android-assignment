package com.obilet.android.assignment.core.data.repository.passenger_filter.abstraction

import com.obilet.android.assignment.core.model.flight_section.PassengerFilter
import kotlinx.coroutines.flow.Flow

interface PassengerFiltersRepository {
    suspend fun insertOrUpdateFilterList(filterList: List<PassengerFilter>): Long

    fun getPassengerFilters(): Flow<List<PassengerFilter>>

}