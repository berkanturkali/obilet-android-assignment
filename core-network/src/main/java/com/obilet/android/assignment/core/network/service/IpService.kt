package com.obilet.android.assignment.core.network.service

import retrofit2.Call
import retrofit2.http.GET

interface IpService {

    @GET("/")
    fun getOutboundIpAddress(): Call<String>

}