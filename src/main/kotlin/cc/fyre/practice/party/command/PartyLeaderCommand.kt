package cc.fyre.practice.party.command

import cc.fyre.practice.Practice
import net.frozenorb.qlib.command.Command
import net.frozenorb.qlib.command.Param
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PartyLeaderCommand {

    @JvmStatic
    @Command(names = ["party leader"],permission = "")
    fun execute(player: Player, @Param(name = "player")target: Player) {
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