package me.tmbot.english.utils

import me.tmbot.english.config.Config
import java.sql.DriverManager

class Database(datasource: Config.Datasource) {

    private val connection = DriverManager.getConnection(datasource.url, datasource.username, datasource.password)

    fun setChatMode(id: Long, mode: String) {
        connection.prepareStatement("update chats set mode = '$mode' where id = $id").execute()
    }

    fun newChat(id: Long) {
        connection.prepareStatement("insert into chats(id,mode) values ($id,'IGNORE')")
    }
}
