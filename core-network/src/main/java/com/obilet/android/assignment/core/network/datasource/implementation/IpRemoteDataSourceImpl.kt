package com.obilet.android.assignment.core.network.datasource.implementation

import com.obilet.android.assignment.core.network.datasource.abstraction.IpRemoteDataSource
import com.obilet.android.assignment.core.network.service.IpService
import retrofit2.Call
import javax.inject.Inject

class IpRemoteDataSourceImpl @Inject constructor(
    private val ipService: IpService
) : IpRemoteDataSource {
    override fun getOutboundIpAddress(): Call<String> {
        return ipService.getOutboundIpAddress()
    }
}