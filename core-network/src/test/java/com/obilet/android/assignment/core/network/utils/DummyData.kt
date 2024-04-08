package com.obilet.android.assignment.core.network.utils

import com.obilet.android.assignment.core.network.model.request.get_session.Application
import com.obilet.android.assignment.core.network.model.request.get_session.GetSessionRequestModel

object DummyData {

    val getSessionRequestModel = GetSessionRequestModel(
        type = 3,
        connection = null,
        application = Application(
            version = "3.1.0.0",
            equipmentId = "DD2A0857-7C7D-4376-A83B-E045435E82BB"
        )
    )
}