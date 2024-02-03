package api.mongo

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.bson.UuidRepresentation
import org.litote.kmongo.KMongo

class Mongo {
    private lateinit var client: MongoClient
    lateinit var db: MongoDatabase

    fun connect() {
        this.client = KMongo.createClient(
            MongoClientSettings
                .builder()
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .applyConnectionString(ConnectionString("mongodb://mongo:mongo@localhost:27017/"))
                .build()
            )
        this.db = client.getDatabase("api")
    }
}

val mongo = Mongo()
