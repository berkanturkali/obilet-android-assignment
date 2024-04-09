package com.obilet.android.assignment.core.data.di

import com.obilet.android.assignment.core.data.repository.client.abstraction.ClientRepository
import com.obilet.android.assignment.core.data.repository.client.implementation.ClientRepositoryImpl
import com.obilet.android.assignment.core.data.repository.ip.abstraction.IpRepository
import com.obilet.android.assignment.core.data.repository.ip.implementation.IpRepositoryImpl
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
}