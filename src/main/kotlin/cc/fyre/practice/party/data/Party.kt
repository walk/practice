package cc.fyre.practice.party.data

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @author Brew
 *
 * @date 11/5/2020
 * @project Practice
 */
class Party(var creator: UUID) {

    var public: Boolean = false
    var joinedQueue: Long = 0L

    val inChat = HashMap<UUID, Boolean>()
    val invites = ArrayList<UUID>()
    val members = ArrayList<UUID>()

    init {
        this.members.add(this.creator)
    }


    fun canJoin(uuid: UUID): Boolean {
        return this.invites.remove(uuid) || this.public
    }


}