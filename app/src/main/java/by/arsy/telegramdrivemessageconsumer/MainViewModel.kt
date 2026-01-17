package by.arsy.telegramdrivemessageconsumer

import android.app.Application
import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val cameraManager: CameraManager = application.getSystemService(
        Context.CAMERA_SERVICE
    ) as CameraManager
    private var cameraId: String? = null

    private var _message = MutableStateFlow("")
    private var _ready = MutableStateFlow(false)
    val message = _message.asStateFlow()
    val ready = _ready.asStateFlow()

    init {
        cameraId = cameraManager.cameraIdList.firstOrNull {
            cameraManager
                .getCameraCharacteristics(it)
                .get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
        }
    }

    fun startBot(token: String) {
        val telegramBotManager = TelegramBotManager(callback = { message ->
            _message.value = message
            driveLightLambda(message)
        })
        telegramBotManager.initBot(botToken = token)
        _ready.value = true
    }

    private fun driveLightLambda(message: String) {
        cameraId?.let {
            if (Command.OnLight.command == message) {
                cameraManager.setTorchMode(it, true)
            }

            if (Command.OffLight.command == message) {
                cameraManager.setTorchMode(it, false)
            }
        }
    }

    override fun onCleared() {
        cameraId?.let {
            cameraManager.setTorchMode(it, false)
        }
        super.onCleared()
    }
}