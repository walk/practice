package cc.fyre.practice.kit

import cc.fyre.practice.Practice
import cc.fyre.practice.kit.data.Kit

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class KitHandler(private val instance: Practice) {

    val cache = HashSet<Kit>()
    val collection = this.instance.databaseHandler.mongoDB.getCollection("kits")

    init {
        this.collection.find().iterator().forEachRemaining{this.cache.add(Practice.instance.gson.fromJson(it.toJson(),Kit::class.java))}
    }

    fun findById(id: String): Kit? {
        return this.cache.firstOrNull{it.id.equals(id,true)}
    }

}