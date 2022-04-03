package me.tmbot.english.bot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.request.SendMessage
import me.tmbot.english.bot.update.BotUpdate
import me.tmbot.english.config.Config
import org.slf4j.LoggerFactory

class Bot {

    private val config = Config()
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun init() {
        val telegramBot = TelegramBot(config.token)
        logger.info("Initialize bot")

        telegramBot.setUpdatesListener { updates ->
            updates.forEach {
                val update = BotUpdate(it)
                telegramBot.execute(SendMessage(update.chatId(), update.text()))
//                when {
//                    update.isCommand() ->
//                }
            }
            UpdatesListener.CONFIRMED_UPDATES_ALL
        }
    }
}
