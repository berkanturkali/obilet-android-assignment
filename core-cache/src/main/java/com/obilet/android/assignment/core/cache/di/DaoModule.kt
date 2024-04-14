package com.obilet.android.assignment.core.cache.di

import com.obilet.android.assignment.core.cache.db.PassengerFiltersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object DaoModule {
    @[Provides Singleton]
    fun providePassengerFilterDao(passengerFiltersDatabase: PassengerFiltersDatabase) =
        passengerFiltersDatabase.getPassengerFiltersDao()
}