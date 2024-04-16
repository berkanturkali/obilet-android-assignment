package com.obilet.android.assignment.feature.flight_section.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obilet.android.assignment.core.data.repository.passenger_filter.abstraction.PassengerFiltersRepository
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter
import com.obilet.android.assignment.feature.bus_section.usecase.FormatDateWithTheGivenPatternUseCase
import com.obilet.android.assignment.feature.bus_section.usecase.GetTodayOrTomorrowDateUseCase
import com.obilet.android.assignment.feature.flight_section.usecase.MakeTheTitlePluralIfTheCountIsGreaterThanOneUseCase
import com.obilet.android.assignment.feature.flight_section.usecase.RemoveParenthesesFromTheTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FlightSectionFragmentViewModel @Inject constructor(
    private val getTodayOrTomorrowDateUseCase: GetTodayOrTomorrowDateUseCase,
    private val passengerFiltersRepository: PassengerFiltersRepository,
    private val removeParenthesesFromTheTitleUseCase: RemoveParenthesesFromTheTitleUseCase,
    private val makeTheTitlePluralIfTheCountIsGreaterThanOneUseCase: MakeTheTitlePluralIfTheCountIsGreaterThanOneUseCase,
    private val formatDateWithTheGivenPatternUseCase: FormatDateWithTheGivenPatternUseCase,
) : ViewModel() {

    companion object {
        private const val DATE_DELIMITER = " "
    }

    var currentRotation = 0f

    var currentRotationOfAddOrRemoveDateButton = 0f

    private val _departureDate = MutableLiveData<Date>()

    val departureDate: LiveData<Date> get() = _departureDate

    private val _returnDate = MutableLiveData<Triple<String, String, String>?>()

    val returnDate: LiveData<Triple<String, String, String>?> get() = _returnDate

    private val _selectedPassengerFilters = MutableLiveData<List<Pair<Int, Int>>>()

    val passengerCount: LiveData<List<Pair<Int, Int>>> get() = _selectedPassengerFilters

    private val _originAndDestinationPair = MutableLiveData<Pair<BusLocation?, BusLocation?>>()

    val originAndDestinationPair: LiveData<Pair<BusLocation?, BusLocation?>> get() = _originAndDestinationPair


    init {
        getPassengerFilterListOrCreateNewList()
        setDepartureDate()
    }

    fun setDepartureDate() {
        val tomorrowDate = getTodayOrTomorrowDateUseCase(isTomorrow = true)
        _departureDate.value = tomorrowDate
    }

    fun formatDepartureDate(departureDate: Date): Triple<String, String, String> {
        val departureDate = formatDateWithTheGivenPatternUseCase(date = departureDate)
        val tomorrowAsList = departureDate.split(DATE_DELIMITER)
        val day = tomorrowAsList.first()
        val month = tomorrowAsList[1]
        val dayOfTheWeek = tomorrowAsList[2]
        return Triple(day, month, dayOfTheWeek)
    }

    fun getPassengerFilterListOrCreateNewList() {
        viewModelScope.launch(Dispatchers.IO) {
            passengerFiltersRepository.getPassengerFilters().collect { passengerFilters ->
                if (passengerFilters.isEmpty()) {
                    passengerFiltersRepository.insertOrUpdateFilterList(PassengerFilter.entries.toList())
                } else {
                    withContext(Dispatchers.Main) {
                        val selectedPassengers =
                            passengerFilters.filter { it.count > 0 }
                        _selectedPassengerFilters.value = selectedPassengers.map {
                            Pair(it.title, it.count)
                        }
                    }
                }
            }
        }
    }

    fun removeParenthesesFromTheTitle(title: String): String {
        return removeParenthesesFromTheTitleUseCase(title)
    }

    fun makeTheTitlePluralIfTheCountIsGreaterThanOne(count: Int, modifiedTitle: String): String {
        return makeTheTitlePluralIfTheCountIsGreaterThanOneUseCase(count, modifiedTitle)
    }

    fun setOriginAndDestination(originAndDestinationPair: Pair<BusLocation?, BusLocation?>) {
        _originAndDestinationPair.value = originAndDestinationPair
    }

}