package cc.fyre.practice.map.command

import cc.fyre.practice.map.data.Map
import net.frozenorb.qlib.command.Command
import net.frozenorb.qlib.command.Param
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

object MapSaveCommand {

    @JvmStatic
    @Command(names = ["map save"],permission = "command.map.save")
    fun execute(sender: CommandSender,@Param(name = "map")map: Map) {

        try {
            map.save()
        } catch (ex: IllegalStateException) {
            sender.sendMessage("${ChatColor.RED}${ex.message}")
        }

    }

}