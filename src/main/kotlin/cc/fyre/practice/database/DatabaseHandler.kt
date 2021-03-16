package cc.fyre.practice.database

import cc.fyre.practice.Practice
import com.mongodb.MongoClient
import com.mongodb.ServerAddress
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions

/**
 * @author Brew
 *
 * @date 11/5/2020
 * @project Practice
 */
class DatabaseHandler(private val instance: Practice) {

    val mongoDB: MongoDatabase
    val mongoPool = MongoClient(ServerAddress("localhost",27017))

    init {
        this.mongoDB = this.mongoPool.getDatabase("practice")
    }

    fun dispose() {
        this.mongoPool.close()
    }

    companion object {
        val UPDATE_OPTIONS: UpdateOptions = UpdateOptions().upsert(true)
    }

}