package com.obilet.android.assignment.core.data.utils

import com.obilet.android.assignment.core.network.model.request.get_session.Application
import com.obilet.android.assignment.core.network.model.request.get_session.GetSessionRequestModel
import com.obilet.android.assignment.core.network.model.response.get_session.GetSessionDTO

object DummyData {

    val sessionDTO = GetSessionDTO(
        sessionId = "CUTta2j0/2Q+xn8D4rdNq2mm/MKZf2XbfGyR28nP5Ro=",
        deviceId = "+hw7S4telFOY7zUV1bj+RD9O4jhE1X6b6YB1B/BixU8=",
        deviceType = 0,
        ipCountry = "TR"
    )

    val getSessionRequestModel = GetSessionRequestModel(
        type = 3,
        connection = null,
        application = Application(
            version = "3.1.0.0",
            equipmentId = "DD2A0857-7C7D-4376-A83B-E045435E82BB"
        )
    )
}