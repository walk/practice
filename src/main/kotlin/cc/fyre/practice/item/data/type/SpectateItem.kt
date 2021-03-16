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
class SpectateItem(private val instance: Practice) : Item() {
    override fun slot(player: Player): Int {
        return this.instance.config.getInt("item.spectate.slot")
    }

    override fun create(player: Player): ItemStack {
        return ItemBuilder.of(Material.valueOf(this.instance.config.getString("item.spectate.material")))
            .name(ChatColor.translateAlternateColorCodes('&',this.instance.config.getString("item.spectate.name")))
            .setLore(this.instance.config.getStringList("item.spectate.lore"))
            .build()
    }

    override fun onInteract(event: PlayerInteractEvent) {
        // @TODO
        // Open page menu of all matches
    }
}