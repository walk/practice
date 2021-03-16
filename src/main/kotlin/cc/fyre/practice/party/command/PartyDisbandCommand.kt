package cc.fyre.practice.party.command

import cc.fyre.practice.Practice
import net.frozenorb.qlib.command.Command
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PartyDisbandCommand {

    @JvmStatic
    @Command(names = ["party disband"],permission = "")
    fun execute(player: Player) {
        val party = Practice.instance.partyHandler.findById(player.uniqueId)

        if(party.members.size != 1) {
            party.findMembers().stream().forEach{it.sendMessage("${ChatColor.RED}Your party was disbanded.")}
            party.findMembers().stream().forEach{party.removeFromParty(it)}
            Practice.instance.partyHandler.cache.remove(party)
            return
        }

        player.sendMessage("${ChatColor.RED}You're not in a party.")
    }

}