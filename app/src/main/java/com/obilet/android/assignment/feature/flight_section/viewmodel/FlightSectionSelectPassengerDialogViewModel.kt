package com.obilet.android.assignment.feature.flight_section.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obilet.android.assignment.core.data.repository.passenger_filter.abstraction.PassengerFiltersRepository
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightSectionSelectPassengerDialogViewModel @Inject constructor(
    private val passengerFiltersRepository: PassengerFiltersRepository
) : ViewModel() {

    private val _passengerFilterList = MutableLiveData<List<PassengerFilter>>()

    val passengerFilterList: LiveData<List<PassengerFilter>> get() = _passengerFilterList

    private val _showLoadingView = MutableLiveData<Boolean>()

    val showLoadingView: LiveData<Boolean> get() = _showLoadingView

    init {
        getPassengerFilterList()
    }

    fun getPassengerFilterList() {
        _showLoadingView.value = true
        viewModelScope.launch {
            passengerFiltersRepository.getPassengerFilters().collect { passengerFilters ->
                _passengerFilterList.value = passengerFilters
                _showLoadingView.value = false
            }
        }
    }

    fun setPassengerFilterList(list: List<PassengerFilter>) {
        _passengerFilterList.value = list
    }

    fun saveFilters() {
        viewModelScope.launch(Dispatchers.IO) {
            passengerFiltersRepository.insertOrUpdateFilterList(
                _passengerFilterList.value ?: emptyList()
            )
        }
    }
}