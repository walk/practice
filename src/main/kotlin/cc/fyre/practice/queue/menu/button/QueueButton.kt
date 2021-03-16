package cc.fyre.practice.queue.menu.button

import cc.fyre.practice.queue.data.Queue
import cc.fyre.practice.queue.menu.QueueEditMenu
import net.frozenorb.qlib.menu.Button
import org.apache.commons.lang.StringUtils
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType

/**
 * @author Brew
 *
 * @date 11/9/2020
 * @project practice
 */
class QueueButton(private val queue: Queue) : Button() {

    override fun getName(player: Player): String {
        return this.queue.displayName
    }

    override fun getDescription(player: Player): MutableList<String> {
        return mutableListOf(

            "${ChatColor.GRAY}${ChatColor.STRIKETHROUGH}${StringUtils.repeat("-",18)}",
            "${ChatColor.YELLOW}Match Type: ${ChatColor.RED}${this.queue.matchType}",
            "${ChatColor.YELLOW}",
            "${ChatColor.GRAY}${ChatColor.STRIKETHROUGH}${StringUtils.repeat("-",18)}"

            )
    }

    override fun getMaterial(player: Player): Material {
        return this.queue.icon
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType) {
        QueueEditMenu(this.queue).openMenu(player)
    }

}