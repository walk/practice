package cc.fyre.practice.party.command

import net.frozenorb.qlib.command.Command
import org.bukkit.entity.Player

object PartyHelpCommand {

    @JvmStatic
    @Command(names = ["p", "p help", "party", "party help"],permission = "")
    fun execute(player: Player) {

        arrayOf(
                "§6§m-----------------------------------------------------",
                "§d§lParty Help §7- §fHow to use party commands.",
                "§6§m-----------------------------------------------------",


                "§9Party Commands:",
                "§e/party join <player> §7- Join a party",
                "§e/party leave §7- Leave your party",
                "§e/party show §7- Get info on a party",


                "",
                "§9Captain Commands:",
                "§e/party invite <player> §7- Invite a player to your party",
                "§e/party kick <player> §7- Kick a player from your party",


                "",
                "§9Leader Commands:",
                "§e/party leader <player> §7- Promote a player to leader",
                "§e/party promote <player> §7- Promote a player to captain",
                "§e/party open §7- Open your party to the public",
                "§e/party close §7- Close your party",
                "§e/party disband §7- Disband your party",


                "",
                "§9Information:",
                "§eTo type in party chat, being your message with §d\"@\"",

                "§6§m-----------------------------------------------------"
        ).forEach{player.sendMessage(it)}

    }
}