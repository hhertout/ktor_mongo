package api

import api.mongo.mongo
import api.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    mongo.connect()
    configureSerialization()
    configureHTTP()
    configureRouting()
}
