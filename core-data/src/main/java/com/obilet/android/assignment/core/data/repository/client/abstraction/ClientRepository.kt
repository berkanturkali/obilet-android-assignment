package com.obilet.android.assignment.core.data.repository.client.abstraction

import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.model.getsession.Session
import com.obilet.android.assignment.core.network.model.request.get_session.GetSessionRequestModel
import kotlinx.coroutines.flow.Flow

interface ClientRepository {
    fun getSession(body: GetSessionRequestModel): Flow<Resource<Session>>
}