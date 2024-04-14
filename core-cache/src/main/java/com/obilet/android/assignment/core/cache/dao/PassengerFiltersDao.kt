package com.obilet.android.assignment.core.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.obilet.android.assignment.core.cache.entity.PassengerFilterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PassengerFiltersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateFilter(filterEntity: PassengerFilterEntity): Long

    @Query("SELECT * FROM passenger_filters")
    fun getPassengerFilters(): Flow<List<PassengerFilterEntity>>
}