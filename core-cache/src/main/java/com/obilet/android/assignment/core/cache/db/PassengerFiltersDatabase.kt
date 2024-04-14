package com.obilet.android.assignment.core.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.obilet.android.assignment.core.cache.BuildConfig
import com.obilet.android.assignment.core.cache.dao.PassengerFiltersDao
import com.obilet.android.assignment.core.cache.entity.PassengerFilterEntity

@Database(
    entities = [PassengerFilterEntity::class],
    version = BuildConfig.passengerFiltersDatabaseVersion,
    exportSchema = false
)
abstract class PassengerFiltersDatabase : RoomDatabase() {
    abstract fun getPassengerFiltersDao(): PassengerFiltersDao

    companion object {
        fun build(context: Context): PassengerFiltersDatabase = Room.databaseBuilder(
            context.applicationContext,
            PassengerFiltersDatabase::class.java,
            BuildConfig.passengerFiltersDatabaseName
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}