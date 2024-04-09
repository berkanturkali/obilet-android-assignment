package com.obilet.android.assignment.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptor @Inject constructor() : Interceptor {
    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val TOKEN_PREFIX = "Basic"
        private const val API_TOKEN = "JEcYcEMyantZV095WVc3G2JtVjNZbWx1"
        private const val TOKEN_WITH_PREFIX = "$TOKEN_PREFIX $API_TOKEN"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        requestBuilder.addHeader(AUTHORIZATION_HEADER, TOKEN_WITH_PREFIX)
        val newRequest: Request = requestBuilder.build()
        return try {
            chain.proceed(newRequest)
        } catch (e: Exception) {
            throw e
        }
    }
}