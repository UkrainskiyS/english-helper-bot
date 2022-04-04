package me.tmbot.english.handler

import com.pengrad.telegrambot.request.SendMessage
import me.tmbot.english.bot.update.BotUpdate
import me.tmbot.english.bot.update.Command.*
import me.tmbot.english.config.Config
import me.tmbot.english.handler.executor.Executor
import me.tmbot.english.utils.Database

class Handler(datasource: Config.Datasource) {

  val database = Database(datasource)

  fun command(update: BotUpdate): SendMessage {
    val chatMode = database.getChatMode(update.chatId())

    return if (chatMode == null && update.command() != START) {
      SendMessage(update.chatId(), "Input command /start!")
    } else {
      when (update.command()) {
        START -> {
          database.newChat(update.chatId())
          SendMessage(update.chatId(), "Hello!")
        }
        VERB -> {
          database.setChatMode(update.chatId(), VERB.name)
          SendMessage(update.chatId(), "Input verb!")
        }
        TRANSLATE -> {
          database.setChatMode(update.chatId(), TRANSLATE.name)
          SendMessage(update.chatId(), "Input text!")
        }
        SIMPLES -> Executor.samples(update.chatId())
        else -> Executor.help(update.chatId())
      }
    }
  }

  fun message(update: BotUpdate): SendMessage {
    return database.getChatMode(update.chatId())?.let {
      when (it) {
        VERB -> Executor.verbs(update.chatId(), update.text())
        TRANSLATE -> Executor.translate(update.chatId(), update.text())
        else -> SendMessage(update.chatId(), "Select command!")
      }
    } ?: SendMessage(update.chatId(), "Input command /start!")
  }
}
