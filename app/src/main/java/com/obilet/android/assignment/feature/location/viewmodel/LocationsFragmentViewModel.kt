package com.obilet.android.assignment.feature.location.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.feature.location.args.LocationsFragmentArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LocationsFragmentViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val DEBOUNCE_DURATION = 300L
    }

    private val _visibilityOfScrollToTopButton = MutableStateFlow(false)

    val visibilityOfScrollToTopButton get() = _visibilityOfScrollToTopButton.asLiveData()


    private val _searchQuery = MutableStateFlow<String?>(null)

    private val _locations
        get() = _searchQuery.debounce(DEBOUNCE_DURATION).filterNotNull()
            .mapLatest { query ->
                if (query.isBlank()) {
                    args.locationList
                } else {
                    val result =
                        findListItemsByQuery(query.trim())
                    result
                }
            }

    val locations get() = _locations.asLiveData()


    lateinit var args: LocationsFragmentArgs

    init {
        savedStateHandle.get<LocationsFragmentArgs>(LocationsFragmentArgs.KEY)?.let { args ->
            this.args = args
        }
        setSearchQuery("")
    }

    private fun findListItemsByQuery(query: String): List<BusLocation> {
        return args.locationList.filter { location ->
            location.name?.lowercase()?.contains(query.lowercase().trim()) ?: false
        }
    }

    fun setVisibilityOfScrollToTopButton(visible: Boolean) {
        _visibilityOfScrollToTopButton.update { visible }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}