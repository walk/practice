package cc.fyre.practice.match

import cc.fyre.practice.Practice
import cc.fyre.practice.match.data.Match
import cc.fyre.practice.match.listener.MatchListener
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * @author Brew
 *
 * @date 11/6/2020
 * @project Practice
 */
class MatchHandler(private val instance: Practice) {

    val cache = HashSet<Match>()
    val collection = this.instance.databaseHandler.mongoDB.getCollection("matches")

    val currentMatches = HashMap<UUID,Match>()
    val spectatingMatches = HashMap<UUID,Match>()

    init {
        this.collection.find().iterator().forEachRemaining{this.cache.add(Practice.instance.gson.fromJson(it.toJson(),Match::class.java))}

        this.instance.server.pluginManager.registerEvents(MatchListener(this.instance),this.instance)
    }

    fun findPlayerMatches(uuid: UUID): MutableSet<Match> {
        return this.cache.filter{it.findPlayers().contains(uuid)}.map{it}.toMutableSet()
    }

    fun isAccessible(uuid: UUID): Boolean {
        return this.currentMatches.keys.contains(uuid) || this.spectatingMatches.keys.contains(uuid)
    }


}