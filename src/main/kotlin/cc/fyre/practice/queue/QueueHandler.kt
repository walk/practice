package cc.fyre.practice.queue

import cc.fyre.practice.Practice
import cc.fyre.practice.database.DatabaseHandler
import cc.fyre.practice.match.data.Match
import cc.fyre.practice.queue.command.QueueEditorCommand
import cc.fyre.practice.queue.data.Queue
import cc.fyre.practice.queue.data.QueuePlayer
import cc.fyre.practice.queue.service.QueueService
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import net.frozenorb.qlib.command.FrozenCommandHandler
import org.bson.Document
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * @author Brew
 *
 * @date 11/9/2020
 * @project practice
 */
class QueueHandler(private val instance: Practice) {

    val cache = HashSet<Queue>()
    val collection: MongoCollection<Document> = this.instance.databaseHandler.mongoDB.getCollection("queues")

    val queue = HashMap<UUID,QueuePlayer>()

    val service = QueueService(this.instance)

    init {
        this.collection.find().forEach{this.cache.add(Practice.instance.gson.fromJson(it.toJson(), Queue::class.java))}

        this.service.runTaskTimer(this.instance,5*20L,5*20L)

        FrozenCommandHandler.registerClass(QueueEditorCommand::class.java)
    }

    fun dispose() {
        this.cache.removeIf{this.update(it)}
    }

    fun findById(id: String): Queue? {
        return this.cache.firstOrNull{it.id.equals(id, true)}
    }

    fun update(queue: Queue): Boolean {
        return this.collection.updateOne(Filters.eq("_id",queue.id),Document("\$set",Document.parse(this.instance.gson.toJson(queue))),DatabaseHandler.UPDATE_OPTIONS).wasAcknowledged()
    }

    fun addToQueue(uuid: UUID,queue: Queue,ranked: Boolean) {

        if(this.queue.containsKey(uuid)) {
            val queuePlayer = QueuePlayer(uuid)

            queuePlayer.queue = queue
            queuePlayer.ranked = ranked

            this.queue.replace(uuid,queuePlayer)
            return
        }

        val queuePlayer = QueuePlayer(uuid)

        queuePlayer.queue = queue
        queuePlayer.ranked = ranked

        this.queue[uuid] = queuePlayer
    }

    fun removeFromQueue(uuid: UUID) {

        if(!this.queue.containsKey(uuid)) {
            return
        }

        this.queue.remove(uuid)
    }

    fun findWaitTime(joined: Long): Int {
        return ChronoUnit.SECONDS.between(Instant.ofEpochSecond(joined), Instant.now()).toInt()
    }

    fun findQueue(uuid: UUID): QueuePlayer? {
        return this.queue[uuid]
    }

    fun isInQueue(uuid: UUID): Boolean {
        return this.queue.containsKey(uuid)
    }

    fun isInQueue(uuid: UUID,queueType: Match.MatchType): Boolean {
        return this.queue[uuid] != null && this.queue[uuid]!!.queue!!.matchType == queueType
    }

}