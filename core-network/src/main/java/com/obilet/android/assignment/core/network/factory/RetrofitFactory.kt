package com.obilet.android.assignment.core.network.factory

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * [RetrofitFactory] is a factory class that is responsible for the creation of [Retrofit] object
 */
class RetrofitFactory @Inject constructor(private val moshi: Moshi) {
    companion object {
        private const val TIME_OUT_DURATION: Long = 120
    }

    fun createRetrofit(
        url: String,
        isDebug: Boolean,
    ): Retrofit {
        val client: OkHttpClient = makeOkHttpClient(
            httpLoggingInterceptor = provideHttpLoggingInterceptor(isDebug = isDebug),
        )
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    private fun provideHttpLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }


    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}