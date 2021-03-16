package cc.fyre.practice.item.data.type

import net.frozenorb.qlib.util.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import cc.fyre.practice.Practice
import cc.fyre.practice.item.data.Item
import cc.fyre.practice.item.menu.QueueMenu

/**
 * @author Brew
 *
 * @date 11/6/2020
 * @project Practice
 */
class UnrankedItem(private val instance: Practice) : Item() {
    override fun slot(player: Player): Int {
        return this.instance.config.getInt("item.unranked.slot")
    }

    override fun create(player: Player): ItemStack {
        return ItemBuilder.of(Material.valueOf(this.instance.config.getString("item.unranked.material")))
            .name(ChatColor.translateAlternateColorCodes('&',this.instance.config.getString("item.unranked.name")))
            .setLore(this.instance.config.getStringList("item.unranked.lore"))
            .build()
    }

    override fun onInteract(event: PlayerInteractEvent) {
        QueueMenu(false).openMenu(event.player)
    }
}