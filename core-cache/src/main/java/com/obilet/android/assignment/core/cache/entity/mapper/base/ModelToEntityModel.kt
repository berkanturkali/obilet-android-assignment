package com.obilet.android.assignment.core.cache.entity.mapper.base

import com.obilet.android.assignment.core.model.mapper.BaseMapper

/**
 * Base class that maps Model type into Entity objects
 *
 * [E] represents Entity models
 * [M] represents Domain models
 */
interface ModelToEntityModel<M, E> : BaseMapper<M, E>