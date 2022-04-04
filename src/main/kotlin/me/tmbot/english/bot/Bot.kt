package me.tmbot.english.bot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import me.tmbot.english.bot.update.BotUpdate
import me.tmbot.english.bot.update.Command
import me.tmbot.english.config.Config
import me.tmbot.english.handler.Handler
import org.slf4j.LoggerFactory

class Bot {

  private val config = Config()
  private val handler = Handler(config.datasource)
  private val logger = LoggerFactory.getLogger(this::class.java)

  fun init() {
    val telegramBot = TelegramBot(config.token)
    logger.info("Initialize bot")

    telegramBot.setUpdatesListener { updates ->
      updates.forEach { update ->
        BotUpdate(update).also {
          when {
            it.isCommand() -> telegramBot.execute(handler.command(it))
            it.isValidText() && handler.database.getChatMode(it.chatId()) != Command.START ->
              telegramBot.execute(handler.message(it))
          }
        }
      }
      UpdatesListener.CONFIRMED_UPDATES_ALL
    }
  }
}
