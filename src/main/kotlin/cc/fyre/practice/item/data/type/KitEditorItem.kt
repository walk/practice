package cc.fyre.practice.item.data.type

import net.frozenorb.qlib.util.ItemBuilder
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
class KitEditorItem(private val instance: Practice) : Item() {
    override fun slot(player: Player): Int {
        return this.instance.config.getInt("item.kiteditor.slot")
    }

    override fun create(player: Player): ItemStack {
        return ItemBuilder.of(Material.valueOf(this.instance.config.getString("item.kiteditor.material")))
            .name(ChatColor.translateAlternateColorCodes('&',this.instance.config.getString("item.kiteditor.name")))
            .setLore(this.instance.config.getStringList("item.kiteditor.lore"))
            .build()
    }

    override fun onInteract(event: PlayerInteractEvent) {
        // @TODO
        // Teleport player to kit editor
    }
}