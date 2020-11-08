package cc.fyre.practice.party.data

import cc.fyre.practice.Practice
import org.bukkit.entity.Player
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
    val captains = ArrayList<UUID>()

    init {
        this.members.add(this.creator)
    }

    fun canJoin(uuid: UUID): Boolean {
        return this.invites.contains(uuid) || this.public
    }

    fun addToParty(player: Player) {

        val party = Practice.instance.partyHandler.findById(player.uniqueId)

        if(party.members.size == 1) {
            Practice.instance.partyHandler.cache.remove(party)
        }

        this.members.add(player.uniqueId)
        this.invites.remove(player.uniqueId)

        // @TODO
        // set party items
    }

    fun removeFromParty(player: Player) {

        val party = Practice.instance.partyHandler.findById(player.uniqueId)

        if(party.members.size == 1) {
            Practice.instance.partyHandler.cache.remove(party)
        }

        this.members.remove(player.uniqueId)

        if(this.inChat.containsKey(player.uniqueId)) {
            this.inChat.remove(player.uniqueId)
        }

        // @TODO
        // remove party items
    }

    fun findMembers(): MutableSet<Player> {
        return this.members.mapNotNull{Practice.instance.server.getPlayer(it)}.toMutableSet()
    }

}