package cc.fyre.practice.party.command

import cc.fyre.carnage.command.data.command.Command
import cc.fyre.carnage.command.data.parameter.Parameter
import cc.fyre.practice.Practice
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PartyLeaderCommand {

    @JvmStatic
    @Command(["party leader"])
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

        party.creator = player.uniqueId

        player.sendMessage("${ChatColor.GOLD}Promoted player ${ChatColor.WHITE}${target.name} ${ChatColor.GOLD}to leader")
        target.sendMessage("${ChatColor.GREEN}You were promoted to leader")
        party.findMembers().stream().forEach{it.sendMessage("${target.name}${ChatColor.GOLD} was promoted to leader.")}
    }
}