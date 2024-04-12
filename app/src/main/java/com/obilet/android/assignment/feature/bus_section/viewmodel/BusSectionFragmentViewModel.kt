package com.obilet.android.assignment.feature.bus_section.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obilet.android.assignment.core.model.BusSectionDay
import com.obilet.android.assignment.feature.bus_section.usecase.GetTodayOrTomorrowDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BusSectionFragmentViewModel @Inject constructor(
    private val getTodayOrTomorrowDateUseCase: GetTodayOrTomorrowDateUseCase
) : ViewModel() {


    private val _selectedDay = MutableLiveData<BusSectionDay>(BusSectionDay.TOMORROW)

    val selectedDay: LiveData<BusSectionDay> get() = _selectedDay

    fun setSelectedDay(day: BusSectionDay) {
        _selectedDay.value = day
    }


    fun getTodayOrderTomorrowDate(isTomorrow: Boolean): String {
        return getTodayOrTomorrowDateUseCase(isTomorrow)
    }
}