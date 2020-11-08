package cc.fyre.practice.item.data.type

import cc.fyre.carnage.util.item.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import cc.fyre.practice.Practice
import cc.fyre.practice.item.data.Item

/**
 * @author Brew
 *
 * @date 11/6/2020
 * @project Practice
 */
class RankedItem(private val instance: Practice) : Item() {
    override fun slot(player: Player): Int {
        return 1
    }

    override fun create(player: Player): ItemStack {
        return ItemBuilder.of(Material.DIAMOND_SWORD)
            .name("${ChatColor.RED}${ChatColor.BOLD}» ${ChatColor.AQUA}${ChatColor.BOLD}Ranked ${ChatColor.GREEN}${ChatColor.BOLD}Queue ${ChatColor.RED}${ChatColor.BOLD}«").build()
    }

    override fun onInteract(event: PlayerInteractEvent) {
        // @TODO
        // Open Ranked Matchmaking Menu
    }
}