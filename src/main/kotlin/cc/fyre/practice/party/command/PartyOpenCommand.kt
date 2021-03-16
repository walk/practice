package cc.fyre.practice.party.command

import cc.fyre.practice.Practice
import net.frozenorb.qlib.command.Command
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object PartyOpenCommand {

    @JvmStatic
    @Command(names = ["party open"],permission = "")
    fun execute(player: Player) {

        val party = Practice.instance.partyHandler.findById(player.uniqueId)

        if(party.creator != player.uniqueId) {
            player.sendMessage("${ChatColor.RED}You're not the leader of this party.")
            return
        }

        party.public = true

        player.sendMessage("${ChatColor.GOLD}Set your party to public")
        party.findMembers().stream().forEach{it.sendMessage("${ChatColor.GOLD}Your party was opened to the public.")}
    }
}