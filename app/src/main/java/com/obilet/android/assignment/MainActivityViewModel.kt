package com.obilet.android.assignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obilet.android.assignment.core.data.repository.client.abstraction.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val clientRepo: ClientRepository
) : ViewModel() {

    init {
        getSession()
    }


    fun getSession() {
        viewModelScope.launch(Dispatchers.Main) {

        }
    }

}