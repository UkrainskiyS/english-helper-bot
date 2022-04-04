package me.tmbot.english.handler.executor

import java.nio.file.Files
import java.nio.file.Path

class Verbs(private val path: String) {

  private val verbs = mutableListOf<List<String>>()
  private val size: Int

  init {
      Files.readAllLines(Path.of(path + "verbs.csv")).forEach {
        verbs.add(it.split("\t"))
      }
    size = verbs[0].size
  }

  fun getInfo(verb: String): String? {
    for (i in 0 until size) {
      verbs.forEach {
        if (it[i] == verb) {
          return it.toString()
        }
      }
    }
    return null
  }
}
