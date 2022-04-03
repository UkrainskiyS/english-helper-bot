package me.tmbot.english.utils

object Words {

    private const val RUSSIAN_LETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
    private const val ENGLISH_LETTERS = "abcdefghijklmnopqrstuvwxyz"

    fun isEnglishText(text: String) = text.find { RUSSIAN_LETTERS.contains(it, ignoreCase = true) } == null

    fun isRussianText(text: String) = text.find { ENGLISH_LETTERS.contains(it, ignoreCase = true) } == null
}
