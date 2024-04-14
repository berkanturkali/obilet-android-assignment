package com.obilet.android.assignment.core.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "passenger_filters")
data class PassengerFilterEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "passenger_filter")
    val passengerFilter: String,
    @ColumnInfo(name = "count")
    val count: Int,
    @ColumnInfo(name = "can_increase")
    val canIncrease: Boolean,
    @ColumnInfo(name = "can_decrease")
    val canDecrease: Boolean,
)