package com.obilet.android.assignment.core.storage

import android.content.Context
import android.content.SharedPreferences
import com.obilet.android.assignment.core.model.getsession.Session
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class manages operations related to
 * the session and the device ids of the user.
 */
@Singleton
class SessionStorageManager @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        private const val SESSION_PREFERENCES = "session_preferences"
        private const val SESSION_ID = "session_id"
        private const val DEVICE_ID = "device_id"
    }

    private val sessionPreferences: SharedPreferences = context.getSharedPreferences(
        SESSION_PREFERENCES, Context.MODE_PRIVATE
    )

    fun cacheSessionAndDeviceId(session: Session) {
        sessionPreferences.edit().apply {
            putString(SESSION_ID, session.sessionId)
            putString(DEVICE_ID, session.deviceId)
            apply()
        }
    }

    fun getSessionId() = sessionPreferences.getString(SESSION_ID, "")

    fun getDeviceId() = sessionPreferences.getString(DEVICE_ID, "")

    fun clearSessionPreferences() {
        sessionPreferences.edit().clear().apply()
    }
}