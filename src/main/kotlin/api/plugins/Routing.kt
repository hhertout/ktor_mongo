package api.plugins

import api.controller.customerRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        customerRoute()
    }
}
