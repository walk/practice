package cc.fyre.practice.queue.command

import cc.fyre.practice.Practice
import cc.fyre.practice.queue.menu.QueueEditorMenu
import net.frozenorb.qlib.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object QueueEditorCommand {

    @JvmStatic
    @Command(names = ["queue edit","queue editor"],permission = "command.queue.edit")
    fun execute(sender: CommandSender) {

        if(sender !is Player) {
            Practice.instance.queueHandler.cache.forEach{sender.sendMessage(it.displayName)}
            return
        }

        QueueEditorMenu().openMenu(sender)
    }

}