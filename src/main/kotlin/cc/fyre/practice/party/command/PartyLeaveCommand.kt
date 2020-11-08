package cc.fyre.practice.party.command

import cc.fyre.carnage.command.data.command.Command
import cc.fyre.practice.Practice
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PartyLeaveCommand {

    @JvmStatic
    @Command(["party leave"])
    fun execute(player: Player) {
        val party = Practice.instance.partyHandler.findById(player.uniqueId)

        if(party.members.size == 1) {
            player.sendMessage("${ChatColor.RED}You're not in a party.")
            return
        }

        party.removeFromParty(player)
        player.sendMessage("${ChatColor.GOLD}Left your party.")
        party.findMembers().stream().forEach{it.sendMessage("${player.name}${ChatColor.GOLD} has left the party.")}
    }

}