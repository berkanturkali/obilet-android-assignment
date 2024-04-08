package com.obilet.android.assignment.core.network.factory

import com.google.common.io.Resources
import com.obilet.android.assignment.core.network.model.response.Status
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.lang.reflect.ParameterizedType
import java.net.URL

internal val moshi: Moshi
    get() = Moshi.Builder()
        .add(
            Status::class.java,
            EnumJsonAdapter.create(Status::class.java).withUnknownFallback(null)
        )
        .add(KotlinJsonAdapterFactory())
        .build()

internal inline fun <reified T, reified M> responseAdapter(): JsonAdapter<T> {
    val type: ParameterizedType = Types.newParameterizedType(
        T::class.java,
        M::class.java,
    )
    return moshi.adapter(type)
}

internal fun getJson(path: String): String {
    val uri: URL = Resources.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}

private val okHttpClient: OkHttpClient
    get() = OkHttpClient.Builder()
        .build()

internal inline fun <reified T> makeApiService(mockWebServer: MockWebServer): T =
    Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(T::class.java)