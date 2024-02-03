package api.dto

import api.models.Customer
import kotlinx.serialization.Serializable
import org.litote.kmongo.newId

@Serializable
data class CustomerDto(val id: String? = null, val name: String, val age: Int)

fun Customer.toDto(): CustomerDto =
    CustomerDto(
        id = this.id.toString(),
        name = this.name,
        age = this.age
    )

fun CustomerDto.toCustomer(): Customer =
    Customer(
        id = newId(),
        name = this.name,
        age = this.age
    )