package api.controller

import api.dto.CustomerDto
import api.dto.toCustomer
import api.dto.toDto
import api.models.Customer
import api.service.CustomerService
import api.utils.Message
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRoute() {
    val service = CustomerService()

    route("/customer") {
        get {
            call.request.queryParameters["id"]
                ?.let { id ->
                    try {
                        service.findById(id)
                            ?.let { customer -> call.respond(customer.toDto()) }
                            ?: call.respond(
                                status = HttpStatusCode.BadRequest,
                                Message("Customer not found")
                            )
                    } catch (err: Exception) {
                        call.respond(
                            status = HttpStatusCode.InternalServerError,
                            Message("Internal server error")
                        )
                    }
                }
                ?: try {
                    val customers = service.findAll().map(Customer::toDto)
                    if (customers.isNotEmpty()) {
                        call.respond(customers)
                    } else {
                        call.respond(status = HttpStatusCode.NoContent, customers)
                    }
                } catch (err: Exception) {
                    call.respond(
                        status = HttpStatusCode.InternalServerError,
                        Message("Internal server error")
                    )
                }
        }

        post {
            try {
                val request = call.receive<CustomerDto>()
                val person = request.toCustomer()
                service.save(person).let { customer ->
                    call.respond(customer.toDto())
                }
            } catch (err: Exception) {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    Message(err.message.toString())
                )
            }
        }
    }
}