package com.obilet.android.assignment.core.network.datasource.abstraction

import retrofit2.Call

interface IpRemoteDataSource {

    fun getOutboundIpAddress(): Call<String>
}