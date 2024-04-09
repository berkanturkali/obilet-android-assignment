package com.obilet.android.assignment.core.network.di

import com.obilet.android.assignment.core.network.BuildConfig
import com.obilet.android.assignment.core.network.factory.RetrofitFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RetrofitModule {

    internal const val OBILET_RETROFIT_NAME = "OBiletRetrofit"

    internal const val IP_ADDRESS_RETROFIT_NAME = "IpAddressRetrofit"

    /**
     * This provides a retrofit instance for the [BuildConfig.BASE_URL]
     */
    @[Provides Singleton Named(OBILET_RETROFIT_NAME)]
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return RetrofitFactory.createRetrofit(
            url = BuildConfig.BASE_URL,
            moshi = moshi,
            client = okHttpClient
        )
    }

    /**
     * This provides a retrofit instance for the [BuildConfig.OUTBOUND_IP_URL]
     */
    @[Provides Singleton Named(IP_ADDRESS_RETROFIT_NAME)]
    fun provideIpAddressRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return RetrofitFactory.createRetrofit(
            url = BuildConfig.OUTBOUND_IP_URL,
            moshi = moshi,
            client = okHttpClient
        )
    }
}