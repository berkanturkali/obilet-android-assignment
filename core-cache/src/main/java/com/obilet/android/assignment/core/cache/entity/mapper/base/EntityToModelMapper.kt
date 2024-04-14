package com.obilet.android.assignment.core.cache.entity.mapper.base

import com.obilet.android.assignment.core.model.mapper.BaseMapper

/**
 * Base class that maps Entity models into Model objects
 *
 * [E] represents Entity models
 * [M] represents Model type
 */
interface EntityToModelMapper<E, M> : BaseMapper<E, M>