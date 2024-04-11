package com.obilet.android.assignment.core.data.usecase

import com.obilet.android.assignment.core.network.model.request.base.BaseRequestModelDTO
import com.obilet.android.assignment.core.network.model.request.device_session.DeviceSessionDTO
import com.obilet.android.assignment.core.storage.SessionStorageManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenerateBaseRequestModelUseCase @Inject constructor(
    private val sessionStorageManager: SessionStorageManager,
    private val generateDateForBaseRequestModelUseCase: GenerateDateForBaseRequestModelUseCase,
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCase,
) {
    operator fun <T> invoke(data: T): BaseRequestModelDTO<T> {
        return BaseRequestModelDTO(
            deviceSessionDTO = DeviceSessionDTO(
                sessionId = sessionStorageManager.getSessionId(),
                deviceId = sessionStorageManager.getDeviceId()
            ),
            date = generateDateForBaseRequestModelUseCase.invoke(),
            language = getDeviceLanguageUseCase.invoke(),
            data = data
        )
    }
}