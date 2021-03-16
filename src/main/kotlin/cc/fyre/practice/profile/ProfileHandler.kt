package cc.fyre.practice.profile

import cc.fyre.practice.Practice
import cc.fyre.practice.profile.data.Profile
import cc.fyre.practice.profile.listener.ProfileListener
import com.mongodb.client.MongoCollection
import org.bson.Document
import java.util.*
import kotlin.collections.HashSet

/**
 * @author Brew
 *
 * @date 11/5/2020
 * @project Practice
 */
class ProfileHandler(private val instance: Practice) {

    val cache = HashSet<Profile>()
    val collection: MongoCollection<Document> = this.instance.databaseHandler.mongoDB.getCollection("profiles")

    init {

        this.instance.server.pluginManager.registerEvents(ProfileListener(this.instance),this.instance)

    }

    fun findById(id: UUID): Optional<Profile> {
        return this.cache.stream().filter{it.id == id}.findFirst()
    }

}