package cc.fyre.practice.queue.menu.button.edit

import cc.fyre.practice.queue.data.Queue
import cc.fyre.practice.queue.menu.edit.QueueEditMapsMenu
import net.frozenorb.qlib.menu.Button
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class QueueEditMapsButton(private val queue: Queue): Button() {

    override fun getName(player: Player): String {
        return "${ChatColor.LIGHT_PURPLE}${ChatColor.BOLD}Match Maps"
    }

    override fun getDescription(player: Player): MutableList<String> {
        val lore = ArrayList<String>()

        lore.add(" ")
        lore.add("${ChatColor.GRAY}Change the maps that players are able to be")
        lore.add("${ChatColor.GRAY}sent too once joining this queue.")

        return lore
    }

    override fun getMaterial(player: Player): Material {
        return Material.COBBLESTONE_STAIRS
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType) {
        QueueEditMapsMenu(this.queue).openMenu(player)
    }

}