package com.obilet.android.assignment.core.network.mapper.bus_journeys

import com.obilet.android.assignment.core.model.Stop
import com.obilet.android.assignment.core.network.mapper.base.RemoteResponseModelMapper
import com.obilet.android.assignment.core.network.model.response.get_bus_journeys.StopDTO
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class StopRemoteResponseMapper @Inject constructor() : RemoteResponseModelMapper<StopDTO, Stop> {

    companion object {
        private const val RESPONSE_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
        private const val TIME_PATTERN = "HH:mm"
    }

    private val responseDateTimeFormat =
        SimpleDateFormat(RESPONSE_DATE_PATTERN, Locale.getDefault())

    private val timeFormat = SimpleDateFormat(TIME_PATTERN, Locale.getDefault())
    override fun mapFromModel(model: StopDTO): Stop {
        return Stop(
            id = model.id,
            name = model.name,
            time = mapResponseDateTimeToTimeFormat(model.time),
            isOrigin = model.isOrigin,
            isDestination = model.isDestination
        )
    }

    private fun mapResponseDateTimeToTimeFormat(responseDateTime: String?): String? {
        return try {
            responseDateTime?.let {
                val date = responseDateTimeFormat.parse(responseDateTime)
                date?.let {
                    timeFormat.format(date)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}