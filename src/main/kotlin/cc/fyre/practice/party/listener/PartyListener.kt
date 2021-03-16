package cc.fyre.practice.party.listener

import cc.fyre.practice.Practice
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

/**
 * @author Brew
 *
 * @date 11/8/2020
 * @project Practice
 */
class PartyListener(private val instance: Practice) : Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = false)
    fun onQuit(event: PlayerQuitEvent) {

        val party = this.instance.partyHandler.findById(event.player.uniqueId)

        if(event.player.uniqueId == party.creator) {
            party.findMembers().stream().forEach{it.sendMessage("${ChatColor.RED}The leader disconnected, therefore your party was disbanded.")}
            party.findMembers().stream().forEach{party.removeFromParty(it)}
            return
        }

        party.removeFromParty(event.player)
        party.findMembers().stream().forEach{it.sendMessage("${ChatColor.RED}${event.player.name} disconnected from the party.")}
    }

}