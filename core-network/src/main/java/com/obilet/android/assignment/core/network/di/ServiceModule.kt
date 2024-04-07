package com.obilet.android.assignment.core.network.di

import com.obilet.android.assignment.core.network.service.ClientService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object ServiceModule {
    @[Provides Singleton]
    fun provideClientService(retrofit: Retrofit): ClientService {
        return retrofit.create()
    }
}