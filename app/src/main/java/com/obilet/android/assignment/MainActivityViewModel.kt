package com.obilet.android.assignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obilet.android.assignment.core.data.repository.client.abstraction.ClientRepository
import com.obilet.android.assignment.core.data.repository.ip.abstraction.IpRepository
import com.obilet.android.assignment.core.data.repository.ip.implementation.IpRepositoryImpl
import com.obilet.android.assignment.core.model.DeviceType
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.model.UiText
import com.obilet.android.assignment.core.network.model.request.get_session.Application
import com.obilet.android.assignment.core.network.model.request.get_session.Connection
import com.obilet.android.assignment.core.network.model.request.get_session.GetSessionRequestModel
import com.obilet.android.assignment.usecase.GetUniqueDeviceIdentifierUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val ipRepo: IpRepository,
    private val clientRepo: ClientRepository,
    private val getUniqueDeviceIdentifier: GetUniqueDeviceIdentifierUseCase
) : ViewModel() {

    var showSplashScreen = true

    private val _showErrorDialog = MutableLiveData<UiText?>()

    val showErrorDialog: LiveData<UiText?> get() = _showErrorDialog

    init {
        getOutboundIPAddress()
    }

    private fun getOutboundIPAddress() {
        viewModelScope.launch(Dispatchers.Main) {
            ipRepo.getOutboundIpAddress().collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {
                        showSplashScreen = false
                        _showErrorDialog.value = resource.error
                    }

                    is Resource.Loading -> {
                        showSplashScreen = true
                    }

                    is Resource.Success -> {
                        /**
                         * Since we are checking if the data is null or empty
                         * in the [IpRepositoryImpl.getOutboundIpAddress]
                         * I am sure that the data is not null
                         */
                        getSession(resource.data!!)
                    }
                }
            }
        }
    }

    private fun getSession(ipAddress: String) {
        viewModelScope.launch(Dispatchers.Main) {
            clientRepo.getSession(
                GetSessionRequestModel(
                    type = DeviceType.ANDROID.type,
                    connection = Connection(
                        ipAddress = ipAddress
                    ),
                    application = Application(
                        version = BuildConfig.VERSION_NAME,
                        equipmentId = getUniqueDeviceIdentifier()
                    ),
                )
            ).collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {
                        showSplashScreen = false
                        _showErrorDialog.value = resource.error
                    }

                    is Resource.Loading -> {
                        showSplashScreen = true
                    }

                    is Resource.Success -> {
                        showSplashScreen = false
                    }
                }

            }
        }
    }


}