package by.arsy.telegramdrivemessageconsumer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel() : ViewModel() {

    private var _message = MutableStateFlow("")
    private var _ready = MutableStateFlow(false)
    val message = _message.asStateFlow()
    val ready = _ready.asStateFlow()

    fun startBot(token: String) {
        val telegramBotManager = TelegramBotManager(callback = { message ->
            _message.value = message
        })
        telegramBotManager.initBot(botToken = token)
        _ready.value = true
    }
}