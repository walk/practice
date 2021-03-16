package cc.fyre.practice.party.command

import cc.fyre.practice.Practice
import net.frozenorb.qlib.command.Command
import net.frozenorb.qlib.command.Param
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PartyJoinCommand {

    @JvmStatic
    @Command(names = ["party join"],permission = "")
    fun execute(player: Player, @Param(name = "player")target: Player) {

        val party = Practice.instance.partyHandler.findById(target.uniqueId)
        val currentParty = Practice.instance.partyHandler.findById(player.uniqueId)

        if(!party.canJoin(player.uniqueId)) {
            player.sendMessage("${ChatColor.RED}You don't have an invite to join this party.")
            return
        }

        if(currentParty.members.size > 1 && party.canJoin(player.uniqueId)) {
            player.sendMessage("${ChatColor.RED}You're already in a party.")
        }

        party.addToParty(player)
        player.sendMessage("${ChatColor.GOLD}Joined ${ChatColor.WHITE}${target.name}'s ${ChatColor.GOLD}party")
    }
}