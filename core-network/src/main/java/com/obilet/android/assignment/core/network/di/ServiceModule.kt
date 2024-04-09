package com.obilet.android.assignment.core.network.di

import com.obilet.android.assignment.core.network.service.ClientService
import com.obilet.android.assignment.core.network.service.IpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object ServiceModule {
    @[Provides Singleton]
    fun provideClientService(@Named(RetrofitModule.OBILET_RETROFIT_NAME) retrofit: Retrofit): ClientService {
        return retrofit.create()
    }

    @[Provides Singleton]
    fun provideIpService(@Named(RetrofitModule.IP_ADDRESS_RETROFIT_NAME) retrofit: Retrofit): IpService {
        return retrofit.create()
    }
}