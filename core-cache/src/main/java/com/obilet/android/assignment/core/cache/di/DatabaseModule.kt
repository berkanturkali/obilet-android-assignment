package com.obilet.android.assignment.core.cache.di

import android.content.Context
import com.obilet.android.assignment.core.cache.db.PassengerFiltersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object DatabaseModule {

    @[Provides Singleton]
    fun providePassengerFiltersDatabase(@ApplicationContext context: Context) =
        PassengerFiltersDatabase.build(context)

}