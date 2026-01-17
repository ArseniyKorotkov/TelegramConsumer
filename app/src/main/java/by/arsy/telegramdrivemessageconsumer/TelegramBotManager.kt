package by.arsy.telegramdrivemessageconsumer

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.BotCommand
import com.pengrad.telegrambot.request.SetMyCommands
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class TelegramBotManager(val callback: (String) -> Unit) {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun initBot(botToken: String) {
        val bot = TelegramBot(botToken)

        scope.launch {
            val commands = arrayOf(
                BotCommand(Command.OnLight.commandDescription, "Light on"),
                BotCommand(Command.OffLight.commandDescription, "Light off")
            )
            bot.execute(SetMyCommands(*commands))
        }

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