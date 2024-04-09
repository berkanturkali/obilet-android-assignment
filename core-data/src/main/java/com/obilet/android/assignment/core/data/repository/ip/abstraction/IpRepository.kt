package com.obilet.android.assignment.core.data.repository.ip.abstraction

import com.obilet.android.assignment.core.model.Resource
import kotlinx.coroutines.flow.Flow

interface IpRepository {

    fun getOutboundIpAddress(): Flow<Resource<String>>
}