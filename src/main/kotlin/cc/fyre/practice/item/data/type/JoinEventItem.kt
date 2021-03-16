package cc.fyre.practice.item.data.type

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import cc.fyre.practice.Practice
import cc.fyre.practice.item.data.Item
import net.frozenorb.qlib.util.ItemBuilder

/**
 * @author Brew
 *
 * @date 11/6/2020
 * @project Practice
 */
class JoinEventItem(private val instance: Practice) : Item() {
    override fun slot(player: Player): Int {
        return this.instance.config.getInt("item.joinevent.slot")
    }

    override fun create(player: Player): ItemStack {
        return ItemBuilder.of(Material.valueOf(this.instance.config.getString("item.joinevent.material")))
            .name(ChatColor.translateAlternateColorCodes('&',this.instance.config.getString("item.joinevent.name")))
            .setLore(this.instance.config.getStringList("item.joinevent.lore"))
            .build()
    }

    override fun onInteract(event: PlayerInteractEvent) {
        // @TODO
        // Add player to event
        // Or tell them there isn't one
        // if no rank and no active event say "u can purchase rank to host event"
        // if no rank and no active event say "no active event"
    }
}