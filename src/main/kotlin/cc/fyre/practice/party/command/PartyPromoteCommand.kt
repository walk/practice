package cc.fyre.practice.party.command

import cc.fyre.carnage.command.data.command.Command
import cc.fyre.carnage.command.data.parameter.Parameter
import cc.fyre.practice.Practice
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PartyPromoteCommand {

    @JvmStatic
    @Command(["party promote"])
    fun execute(player: Player, @Parameter("player")target: Player) {
        val party = Practice.instance.partyHandler.findById(player.uniqueId)

        if(party.creator != player.uniqueId) {
            player.sendMessage("${ChatColor.RED}You're not the leader of this party.")
            return
        }

        if(!party.members.contains(target.uniqueId)) {
            player.sendMessage("${ChatColor.RED}That player isn't in your party")
            return
        }

        if(party.captains.contains(target.uniqueId)) {
            player.sendMessage("${ChatColor.WHITE}${target.name} ${ChatColor.RED}is already a captain")
            return
        }

        party.captains.add(target.uniqueId)

        player.sendMessage("${ChatColor.GOLD}Promoted player ${ChatColor.WHITE}${target.name}")
        target.sendMessage("${ChatColor.GREEN}You were promoted to captain")
        party.findMembers().stream().forEach{it.sendMessage("${target.name}${ChatColor.GOLD} was promoted to captain.")}
    }
}