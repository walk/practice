package cc.fyre.practice.party.command

import cc.fyre.practice.Practice
import mkremins.fanciful.FancyMessage
import net.frozenorb.qlib.command.Command
import net.frozenorb.qlib.command.Param
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PartyInviteCommand {

    @Command(names = ["party invite"],permission = "")
    fun execute(player: Player,@Param(name = "target")target: Player) {

        val party = Practice.instance.partyHandler.findById(player.uniqueId)

        if(party.creator != player.uniqueId || party.captains.contains(player.uniqueId)) {
            player.sendMessage("${ChatColor.RED}Your role isn't high enough to do this.")
            return
        }

        if(party.invites.contains(target.uniqueId)) {
            player.sendMessage("${ChatColor.RED}That player already has a pending invite.")
            return
        }

        if(party.members.contains(target.uniqueId)) {
            player.sendMessage("${ChatColor.RED}That player is already in your party")
            return
        }

        party.invites.add(target.uniqueId)

        val fancyMessage = FancyMessage()
        fancyMessage.text("${ChatColor.GOLD}You have been invited to join ${ChatColor.WHITE}${player.name}'s ${ChatColor.GOLD}party.")
        fancyMessage.command("/party join ${player.name}").tooltip("${ChatColor.GREEN}Click to join")
        fancyMessage.send(target)

        player.sendMessage("${ChatColor.GOLD}Invited player ${ChatColor.WHITE}${target.name} ${ChatColor.GOLD}to your party.")
    }

}