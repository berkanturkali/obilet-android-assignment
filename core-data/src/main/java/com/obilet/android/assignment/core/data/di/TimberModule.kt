package com.obilet.android.assignment.core.data.di

import com.obilet.android.assignment.core.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface TimberModule {

    companion object {
        @[Provides Singleton]
        fun provideTimberTree(): Timber.Tree = object : Timber.DebugTree() {
            override fun isLoggable(tag: String?, priority: Int): Boolean {
                return BuildConfig.DEBUG
            }
        }
    }
}