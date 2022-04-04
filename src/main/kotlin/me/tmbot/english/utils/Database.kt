package me.tmbot.english.utils

import me.tmbot.english.bot.update.Command
import me.tmbot.english.config.Config
import java.sql.DriverManager

class Database(datasource: Config.Datasource) {

  private val connection = DriverManager.getConnection(datasource.url, datasource.username, datasource.password)

  fun setChatMode(id: Long, mode: String) {
    connection.prepareStatement("update chats set mode = '$mode' where id = $id").use { it.execute() }
  }

  fun newChat(id: Long) {
    connection.prepareStatement("insert into chats(id) values ($id)").use { it.execute() }
  }

  fun getChatMode(id: Long): Command? {
    val exist = connection.prepareStatement("select exists(select * from chats where id = $id)").use {
      val rs = it.executeQuery()
      rs.next()
      rs.getBoolean(1)
    }

    return if (exist) {
      Command.valueOf(
        connection.prepareStatement("select mode from chats where id = $id").use {
          val rs = it.executeQuery()
          rs.next()
          rs.getString(1)
        }
      )
    } else {
      null
    }
  }
}
