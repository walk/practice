package cc.fyre.practice.queue.menu.button.edit

import cc.fyre.practice.queue.data.Queue
import cc.fyre.practice.queue.menu.edit.QueueEditMatchTypeMenu
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
class QueueEditMatchTypeButton(private val queue: Queue): Button() {

    override fun getName(player: Player): String {
        return "${ChatColor.GOLD}${ChatColor.BOLD}Match Type"
    }

    override fun getDescription(player: Player): MutableList<String> {
        val lore = ArrayList<String>()

        lore.add(" ")
        lore.add("${ChatColor.GRAY}Change the match type that this queue is linked")
        lore.add("${ChatColor.GRAY}too.")

        return lore
    }

    override fun getMaterial(player: Player): Material {
        return Material.DIAMOND_SWORD
    }

    override fun clicked(player: Player,slot: Int,clickType: ClickType) {
        QueueEditMatchTypeMenu(this.queue).openMenu(player)
    }

}