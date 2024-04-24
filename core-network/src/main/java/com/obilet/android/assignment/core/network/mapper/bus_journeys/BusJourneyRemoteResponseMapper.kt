package com.obilet.android.assignment.core.network.mapper.bus_journeys

import android.content.Context
import com.obilet.android.assignment.core.model.BusJourney
import com.obilet.android.assignment.core.model.Currency
import com.obilet.android.assignment.core.model.R.string
import com.obilet.android.assignment.core.network.mapper.base.RemoteResponseModelMapper
import com.obilet.android.assignment.core.network.model.response.get_bus_journeys.BusJourneyDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class BusJourneyRemoteResponseMapper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val stopRemoteResponseMapper: StopRemoteResponseMapper,
    private val featureRemoteResponseMapper: FeatureRemoteResponseMapper,
) :
    RemoteResponseModelMapper<BusJourneyDTO, BusJourney> {

    companion object {
        private const val TIME_PATTERN = "HH:mm:ss"
        private const val HOUR_PATTERN = "H"
        private const val MIN_PATTERN = "m"
    }

    private val timeFormatter = SimpleDateFormat(TIME_PATTERN, Locale.getDefault())

    private val hourFormatter = SimpleDateFormat(HOUR_PATTERN, Locale.getDefault())

    private val minuteFormatter = SimpleDateFormat(MIN_PATTERN, Locale.getDefault())

    override fun mapFromModel(model: BusJourneyDTO): BusJourney {
        return BusJourney(
            id = model.id,
            partnerId = model.partnerId,
            partnerName = model.partnerName,
            routeId = model.routeId,
            busType = model.busType,
            totalSeats = model.totalSeats,
            availableSeats = model.availableSeats,
            journeyOrigin = model.journey?.origin,
            journeyDestination = model.journey?.destination,
            journeyDeparture = model.journey?.departure,
            journeyArrival = model.journey?.arrival,
            journeyDuration = formatDurationForDisplay(model.journey?.duration),
            journeyPrice = formatThePrice(
                model.journey?.originalPrice?.toString(),
                model.journey?.currency
            ),
            journeyBusName = model.journey?.busName,
            originLocation = model.originLocation,
            destinationLocation = model.destinationLocation,
            originLocationId = model.originLocationId,
            destinationLocationId = model.destinationLocationId,
            stops = stopRemoteResponseMapper.mapModelList(model.journey?.stops ?: emptyList()),
            features = featureRemoteResponseMapper.mapModelList(model.features ?: emptyList())


        )
    }

    private fun formatDurationForDisplay(time: String?): String? {
        return try {
            time?.let {
                val date = timeFormatter.parse(time)
                date?.let {
                    val hours = hourFormatter.format(date)
                    val minutes = minuteFormatter.format(date)
                    return if (minutes == "0") {
                        context.getString(string.durationWithHour, hours)
                    } else {
                        context.getString(string.duration, hours, minutes)
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun formatThePrice(price: String?, currency: String?): String? {
        return price?.let {
            when (currency) {
                Currency.TRY.isoCode -> {
                    price + " " + Currency.TRY.symbol
                }

                Currency.USD.isoCode -> {
                    price + " " + Currency.USD.symbol
                }

                Currency.EUR.isoCode -> {
                    price + " " + Currency.EUR.symbol
                }

                else -> {
                    price
                }
            }
        }
    }
}