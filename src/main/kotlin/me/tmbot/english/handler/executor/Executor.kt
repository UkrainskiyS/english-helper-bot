package me.tmbot.english.handler.executor

import com.pengrad.telegrambot.request.SendMessage
import java.nio.file.Files
import java.nio.file.Path

object Executor {
  private const val RESOURCES_PATH = "src/main/resources/assets/"

  private val verbs = Verbs(RESOURCES_PATH)

  fun verbs(chatId: Long, verb: String): SendMessage {
    return SendMessage(chatId, verbs.getInfo(verb) ?: "Verb not found!")
  }

  fun translate(chatId: Long, verb: String): SendMessage {
    return SendMessage(chatId, verb)
  }

  fun help(chatId: Long): SendMessage {
    return SendMessage(chatId, Files.readAllBytes(Path.of(RESOURCES_PATH + "help.txt")).toString())
  }

  fun samples(chatId: Long): SendMessage {
    return SendMessage(chatId, Files.readAllBytes(Path.of(RESOURCES_PATH + "samples.txt")).toString())
  }
}
