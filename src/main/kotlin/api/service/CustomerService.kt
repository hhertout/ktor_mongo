package api.service

import api.models.Customer
import api.mongo.mongo
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.id.toId

class CustomerService: ServiceInterface<Customer> {
    private val collection = mongo.db.getCollection<Customer>()

    override fun findAll(): List<Customer> {
        return collection.find().toList()
    }

    override fun findById(id: String): Customer? {
        return collection.findOne(Customer::id eq ObjectId(id).toId())
    }

    override fun save(entity: Customer): Customer {
        collection.insertOne(entity)
        return entity
    }
}