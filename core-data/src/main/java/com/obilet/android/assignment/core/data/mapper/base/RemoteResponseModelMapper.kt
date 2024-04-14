package com.obilet.android.assignment.core.data.mapper.base

import com.obilet.android.assignment.core.model.mapper.BaseMapper

/**
 * Base class that maps Remote models into Domain objects
 *
 * [M] represents Remote models
 * [D] represents Domain models
 */
interface RemoteResponseModelMapper<M, D> : BaseMapper<M, D>