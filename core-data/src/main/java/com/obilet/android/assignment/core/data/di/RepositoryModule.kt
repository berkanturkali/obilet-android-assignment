package com.obilet.android.assignment.core.data.di

import com.obilet.android.assignment.core.data.repository.client.abstraction.ClientRepository
import com.obilet.android.assignment.core.data.repository.client.implementation.ClientRepositoryImpl
import com.obilet.android.assignment.core.data.repository.ip.abstraction.IpRepository
import com.obilet.android.assignment.core.data.repository.ip.implementation.IpRepositoryImpl
import com.obilet.android.assignment.core.data.repository.journey.abstraction.JourneyRepository
import com.obilet.android.assignment.core.data.repository.journey.implementation.JourneyRepositoryImpl
import com.obilet.android.assignment.core.data.repository.location.abstraction.LocationRepository
import com.obilet.android.assignment.core.data.repository.location.implementation.LocationRepositoryImpl
import com.obilet.android.assignment.core.data.repository.passenger_filter.abstraction.PassengerFiltersRepository
import com.obilet.android.assignment.core.data.repository.passenger_filter.implementation.PassengerFiltersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface RepositoryModule {

    @get:Binds
    val ClientRepositoryImpl.clientRepository: ClientRepository

    @get:Binds
    val IpRepositoryImpl.ipRepository: IpRepository

    @get:Binds
    val LocationRepositoryImpl.locationRepository: LocationRepository

    @get:Binds
    val PassengerFiltersRepositoryImpl.passengerFiltersRepository: PassengerFiltersRepository


    @get:Binds
    val JourneyRepositoryImpl.journeyRepository: JourneyRepository
}