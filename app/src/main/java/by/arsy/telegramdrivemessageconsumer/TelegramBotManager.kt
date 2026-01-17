package by.arsy.telegramdrivemessageconsumer

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class TelegramBotManager(val callback: (String) -> Unit) {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun initBot(botToken: String) {
        val bot = TelegramBot(botToken)
        bot.setUpdatesListener { updates ->
            for (update in updates) {
                if (update.message() != null && update.message().text() != null) {
                    val userText = update.message().text()
                    scope.launch {
                        callback(userText)
                    }
                }
            }
            UpdatesListener.CONFIRMED_UPDATES_ALL
        }
    }
}