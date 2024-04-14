package com.obilet.android.assignment.feature.flight_section.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FlightSectionSelectPassengerDialogViewModel @Inject constructor() : ViewModel() {

    private val _passengerFilterList = MutableLiveData<List<PassengerFilter>>()

    val passengerFilterList: LiveData<List<PassengerFilter>> get() = _passengerFilterList

    init {
        getPassengerFilterList()
    }

    fun getPassengerFilterList() {
        _passengerFilterList.value = PassengerFilter.entries.toList()
    }


    fun setPassengerFilterList(list: List<PassengerFilter>) {
        _passengerFilterList.value = list
    }
}