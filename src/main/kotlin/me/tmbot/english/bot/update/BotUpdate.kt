package me.tmbot.english.bot.update

import com.pengrad.telegrambot.model.Update

class BotUpdate(private val update: Update) {

    fun chatId(): Long = update.message().chat().id()

    fun text(): String = update.message().text()

    fun isValidText() = update.message() != null && update.message().text() != null

    fun isCommand() = command() != null

    fun command() = Command.parse(update.message().text())
}
