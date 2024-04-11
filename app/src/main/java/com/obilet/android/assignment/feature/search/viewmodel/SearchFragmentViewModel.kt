package com.obilet.android.assignment.feature.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obilet.android.assignment.core.data.repository.location.abstraction.LocationRepository
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _busLocations = MutableLiveData<Resource<List<BusLocation>>>()

    val busLocations: LiveData<Resource<List<BusLocation>>> get() = _busLocations

    init {
        getBusLocations(null)
    }

    fun getBusLocations(query: String?) {
        viewModelScope.launch(Dispatchers.Main) {
            locationRepository.getBusLocations(query).onEach {
                _busLocations.value = it
            }
        }
    }
}