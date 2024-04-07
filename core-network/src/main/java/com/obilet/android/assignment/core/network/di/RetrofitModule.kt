package com.obilet.android.assignment.core.network.di

import com.obilet.android.assignment.core.network.BuildConfig
import com.obilet.android.assignment.core.network.factory.RetrofitFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RetrofitModule {
    @[Provides Singleton]
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return RetrofitFactory.createRetrofit(
            url = BuildConfig.BASE_URL,
            isDebug = BuildConfig.DEBUG,
            moshi = moshi
        )
    }
}