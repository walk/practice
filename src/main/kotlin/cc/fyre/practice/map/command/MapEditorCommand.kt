package cc.fyre.practice.map.command

import cc.fyre.practice.map.menu.MapEditorMenu
import net.frozenorb.qlib.command.Command
import org.bukkit.entity.Player

object MapEditorCommand {

    @JvmStatic
    @Command(names = ["map editor"],permission = "command.map.editor")
    fun execute(player: Player) {
        MapEditorMenu().openMenu(player)
    }

}