package com.obilet.android.assignment.feature.flight_section.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obilet.android.assignment.feature.bus_section.usecase.GetTodayOrTomorrowDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FlightSectionFragmentViewModel @Inject constructor(
    private val getTodayOrTomorrowDateUseCase: GetTodayOrTomorrowDateUseCase
) : ViewModel() {

    companion object {
        private const val DATE_DELIMITER = " "
    }

    private val _departureDate = MutableLiveData<Triple<String, String, String>>()

    val defaultDepartureDate: LiveData<Triple<String, String, String>> get() = _departureDate

    private val _returnDate = MutableLiveData<Triple<String, String, String>?>()

    val returnDate: LiveData<Triple<String, String, String>?> get() = _returnDate

    private val _passengerCount = MutableLiveData<Int>()

    val passengerCount: LiveData<Int> get() = _passengerCount

    init {
        setDepartureDate()
        setPassengerCount(1)
    }

    fun setDepartureDate() {
        val tomorrow = getTodayOrTomorrowDateUseCase(isTomorrow = true)
        val tomorrowAsList = tomorrow.split(DATE_DELIMITER)
        val day = tomorrowAsList.first()
        val month = tomorrowAsList[1]
        val dayOfTheWeek = tomorrowAsList[2]
        _departureDate.value = Triple(day, month, dayOfTheWeek)
    }

    fun setPassengerCount(count: Int) {
        _passengerCount.value = count
    }

}