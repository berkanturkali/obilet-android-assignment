package com.obilet.android.assignment.core.data.repository.passenger_filter.implementation

import com.obilet.android.assignment.core.cache.dao.PassengerFiltersDao
import com.obilet.android.assignment.core.cache.entity.mapper.PassengerFilterEntityToPassengerFilterMapper
import com.obilet.android.assignment.core.cache.entity.mapper.PassengerFilterToPassengerFilterEntityMapper
import com.obilet.android.assignment.core.data.repository.passenger_filter.abstraction.PassengerFiltersRepository
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PassengerFiltersRepositoryImpl @Inject constructor(
    private val passengerFiltersDao: PassengerFiltersDao,
    private val passengerFilterEntityToPassengerFilterMapper: PassengerFilterEntityToPassengerFilterMapper,
    private val passengerFilterToPassengerFilterEntityMapper: PassengerFilterToPassengerFilterEntityMapper,
) : PassengerFiltersRepository {
    override suspend fun insertOrUpdateFilterList(filterList: List<PassengerFilter>) {
        val entityList = passengerFilterToPassengerFilterEntityMapper.mapModelList(filterList)
        passengerFiltersDao.insertOrUpdateFilterList(entityList)
    }

    override fun getPassengerFilters(): Flow<List<PassengerFilter>> {
        return passengerFiltersDao.getPassengerFilters()
            .map(passengerFilterEntityToPassengerFilterMapper::mapModelList)
    }
}