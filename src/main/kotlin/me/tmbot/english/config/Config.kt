package me.tmbot.english.config

import org.yaml.snakeyaml.Yaml
import java.io.FileReader

class Config {

    val token: String
    val datasource = Datasource()

    init {
        val config: Map<String, Any> = Yaml().load(FileReader("src/main/resources/application.yaml"))

        token = (config["bot"] as Map<*, *>)["token"].toString()

        val datasourceConfig = config["datasource"] as Map<*, *>
        datasource.url = datasourceConfig["url"].toString()
        datasource.username = datasourceConfig["username"].toString()
        datasource.password = datasourceConfig["password"].toString()
    }

    class Datasource {
        lateinit var url: String
        lateinit var username: String
        lateinit var password: String
    }
}
