package com.obilet.android.assignment.core.network.mapper.bus_journeys

import com.obilet.android.assignment.core.model.Feature
import com.obilet.android.assignment.core.network.BuildConfig
import com.obilet.android.assignment.core.network.mapper.base.RemoteResponseModelMapper
import com.obilet.android.assignment.core.network.model.response.get_bus_journeys.FeatureDTO
import javax.inject.Inject

class FeatureRemoteResponseMapper @Inject constructor() :
    RemoteResponseModelMapper<FeatureDTO, Feature> {

    override fun mapFromModel(model: FeatureDTO): Feature {
        return Feature(
            name = model.name,
            imageUrl = BuildConfig.FEATURE_IMAGE_BASE_URL + "${model.id}.svg"
        )
    }
}