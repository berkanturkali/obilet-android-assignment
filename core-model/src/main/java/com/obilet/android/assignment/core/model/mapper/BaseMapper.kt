package com.obilet.android.assignment.core.model.mapper

interface BaseMapper<T, R> {

    fun mapFromModel(model: T): R

    fun mapModelList(models: List<T>): List<R> {
        return models.mapTo(mutableListOf(), ::mapFromModel)
    }
}