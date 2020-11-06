package cc.fyre.practice.database

import cc.fyre.practice.Practice
import cc.fyre.symbiote.SymbioteAPI
import cc.fyre.symbiote.type.RedisSymbioteAPI
import cc.fyre.venom.database.jedis.CustomPoolConfig
import com.mongodb.MongoClient
import com.mongodb.ServerAddress
import com.mongodb.client.MongoDatabase
import redis.clients.jedis.JedisPool

/**
 * @author Brew
 *
 * @date 11/5/2020
 * @project Practice
 */
class DatabaseHandler(private val instance: Practice) {

    val symbiote: SymbioteAPI

    val mongoDB: MongoDatabase
    val mongoPool = MongoClient(ServerAddress("51.79.73.197",27017))
    val redisPool = JedisPool(CustomPoolConfig(),"51.79.73.197",6379,30000)

    init {
        this.mongoDB = this.mongoPool.getDatabase("Practice")
        this.symbiote = RedisSymbioteAPI(this.redisPool,"practice")
    }

    fun dispose() {
        this.redisPool.close()
    }

}