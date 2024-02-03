package api.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
data class Customer(
    @BsonId val id: Id<Customer>? = newId(),
    val name: String,
    val age: Int,
)