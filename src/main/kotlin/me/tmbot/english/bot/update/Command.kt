package me.tmbot.english.bot.update

enum class Command {
    START {
        override fun `is`(command: String) = command.matches(Regex("/start@?.*"))
    },
    HELP {
        override fun `is`(command: String) = command.matches(Regex("/help@?.*"))
    },
    VERB {
        override fun `is`(command: String) = command.matches(Regex("/verb@?.*"))
    },
    SIMPLES {
        override fun `is`(command: String) = command.matches(Regex("/simples@?.*"))
    },
    TRANSLATE {
        override fun `is`(command: String) = command.matches(Regex("/translate@?.*"))
    };

    abstract fun `is`(command: String): Boolean

    companion object {
        fun parse(command: String): Command? {
            return when {
                START.`is`(command) -> START
                HELP.`is`(command) -> HELP
                VERB.`is`(command) -> VERB
                SIMPLES.`is`(command) -> SIMPLES
                TRANSLATE.`is`(command) -> TRANSLATE
                else -> null
            }
        }
    }
}
