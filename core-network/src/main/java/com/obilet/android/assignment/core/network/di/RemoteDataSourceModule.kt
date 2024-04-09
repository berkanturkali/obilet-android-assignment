package com.obilet.android.assignment.core.network.di

import com.obilet.android.assignment.core.network.datasource.abstraction.ClientRemoteDataSource
import com.obilet.android.assignment.core.network.datasource.abstraction.IpRemoteDataSource
import com.obilet.android.assignment.core.network.datasource.implementation.ClientRemoteDataSourceImpl
import com.obilet.android.assignment.core.network.datasource.implementation.IpRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module class that provides/binds implementation of remote data sources.
 */
@[Module InstallIn(SingletonComponent::class)]
interface RemoteDataSourceModule {

    @get:Binds
    val ClientRemoteDataSourceImpl.clientRemoteDataSource: ClientRemoteDataSource

    @get:Binds
    val IpRemoteDataSourceImpl.ipRemoteDataSource: IpRemoteDataSource

}