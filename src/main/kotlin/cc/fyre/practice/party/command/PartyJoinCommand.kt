package cc.fyre.practice.party.command

import cc.fyre.carnage.command.data.command.Command
import cc.fyre.carnage.command.data.parameter.Parameter
import cc.fyre.practice.Practice
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PartyJoinCommand {

    @JvmStatic
    @Command(["party join"])
    fun execute(player: Player, @Parameter("player")target: Player) {

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