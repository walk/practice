package cc.fyre.practice.party.command

import cc.fyre.practice.Practice
import net.frozenorb.qlib.command.Command
import net.frozenorb.qlib.command.Param
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PartyKickCommand {

    @JvmStatic
    @Command(names = ["party kick"],permission = "")
    fun execute(player: Player, @Param(name = "player")target: Player) {
        val party = Practice.instance.partyHandler.findById(player.uniqueId)

        if(party.creator != player.uniqueId || party.captains.contains(player.uniqueId)) {
            player.sendMessage("${ChatColor.RED}Your role isn't high enough to do this.")
            return
        }

        if(!party.members.contains(target.uniqueId)) {
            player.sendMessage("${ChatColor.RED}Invalid Party Member.")
            return
        }

        party.removeFromParty(target)
        player.sendMessage("${ChatColor.GOLD}Kicked player ${ChatColor.WHITE}${target.name} ${ChatColor.GOLD}from your party")
        target.sendMessage("${ChatColor.RED}You were kicked from your party!")
        party.findMembers().stream().forEach{it.sendMessage("${target.name}${ChatColor.GOLD} was kicked from the party")}
    }

}