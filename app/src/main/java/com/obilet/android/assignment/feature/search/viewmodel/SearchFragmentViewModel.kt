package com.obilet.android.assignment.feature.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obilet.android.assignment.core.data.repository.location.abstraction.LocationRepository
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.feature.search.usecase.FindDefaultLocationsByCityIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val findDefaultLocationsByCityIdUseCase: FindDefaultLocationsByCityIdUseCase
) : ViewModel() {

    private val _busLocations = MutableLiveData<Resource<List<BusLocation>>>()

    val busLocations: LiveData<Resource<List<BusLocation>>> get() = _busLocations

    private val _originAndDestinationPair = MutableLiveData<Pair<BusLocation?, BusLocation?>>()

    val originAndDestinationPair: LiveData<Pair<BusLocation?, BusLocation?>> get() = _originAndDestinationPair

    init {
        getBusLocations(null)
    }

    fun getBusLocations(query: String?) {
        viewModelScope.launch(Dispatchers.Main) {
            locationRepository.getBusLocations(query).collect { resource ->
                _busLocations.value = resource
                if (resource is Resource.Success) {
                    val locations = resource.data
                    if (locations!!.size >= 2) {
                        val originAndDestinationPair =
                            findDefaultLocationsByCityId(locations)
                        if (originAndDestinationPair.first != null && originAndDestinationPair.second != null) {
                            setOriginAndDestination(originAndDestinationPair)
                        }
                    }
                }
            }
        }
    }

    fun findDefaultLocationsByCityId(locationList: List<BusLocation>): Pair<BusLocation?, BusLocation?> {
        return findDefaultLocationsByCityIdUseCase(locationList)
    }

    fun setOriginAndDestination(originAndDestinationPair: Pair<BusLocation?, BusLocation?>) {
        _originAndDestinationPair.value = originAndDestinationPair
    }
}