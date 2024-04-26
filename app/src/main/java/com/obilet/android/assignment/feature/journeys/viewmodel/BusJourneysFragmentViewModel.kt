package com.obilet.android.assignment.feature.journeys.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obilet.android.assignment.core.data.repository.journey.abstraction.JourneyRepository
import com.obilet.android.assignment.core.model.BusJourney
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.network.model.request.get_bus_journeys.GetBusJourneysRequestModel
import com.obilet.android.assignment.feature.journeys.args.BusJourneysFragmentArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusJourneysFragmentViewModel @Inject constructor(
    private val journeyRepo: JourneyRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {


    private val _busJourneys = MutableLiveData<Resource<List<BusJourney>>>()

    val busJourneys: LiveData<Resource<List<BusJourney>>> get() = _busJourneys

    private val _toolbarTitle = MutableLiveData<String>()

    val toolbarTitle: LiveData<String> get() = _toolbarTitle

    lateinit var args: BusJourneysFragmentArgs

    init {
        savedStateHandle.get<BusJourneysFragmentArgs>(BusJourneysFragmentArgs.BUS_JOURNEYS_FRAGMENT_ARGS_KEY)
            ?.let { args ->
                this.args = args
                _toolbarTitle.value = args.originName + "\n" + args.destinationName
                fetchBusJourneys(
                    GetBusJourneysRequestModel(
                        originId = args.originId,
                        destinationId = args.destinationId,
                        departureDate = args.departureDate
                    )
                )
            }

    }

    fun fetchBusJourneys(body: GetBusJourneysRequestModel) {
        viewModelScope.launch(Dispatchers.Main) {
            journeyRepo.getBusJourneys(body).collect { resource ->
                _busJourneys.value = resource
            }
        }
    }
}