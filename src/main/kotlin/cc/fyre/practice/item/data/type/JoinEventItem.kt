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
class JoinEventItem(private val instance: Practice) : Item() {
    override fun slot(player: Player): Int {
        return 7
    }

    override fun create(player: Player): ItemStack {
        return ItemBuilder.of(Material.EMERALD)
            .name("${ChatColor.DARK_BLUE}${ChatColor.BOLD}» ${ChatColor.DARK_PURPLE}${ChatColor.BOLD}Join Event ${ChatColor.DARK_BLUE}${ChatColor.BOLD}«").build()
    }

    override fun onInteract(event: PlayerInteractEvent) {
        // @TODO
        // Add player to event
        // Or tell them there isn't one
        // if no rank and no active event say "u can purchase rank to host event"
        // if no rank and no active event say "no active event"
    }
}