package com.obilet.android.assignment.core.data.mapper.getsession

import com.obilet.android.assignment.core.data.mapper.base.RemoteResponseModelMapper
import com.obilet.android.assignment.core.model.getsession.Session
import com.obilet.android.assignment.core.network.model.response.get_session.GetSessionDTO
import javax.inject.Inject

class SessionRemoteResponseMapper @Inject constructor() :
    RemoteResponseModelMapper<GetSessionDTO, Session> {
    override fun mapFromModel(model: GetSessionDTO): Session {
        return Session(
            deviceId = model.deviceId,
            sessionId = model.sessionId
        )
    }
}