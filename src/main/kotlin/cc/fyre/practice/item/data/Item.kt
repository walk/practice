package cc.fyre.practice.item.data

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import cc.fyre.practice.Practice

/**
 * @author Brew
 *
 * @date 11/6/2020
 * @project Practice
 */
abstract class Item {

    abstract fun slot(player: Player):Int
    abstract fun create(player: Player): ItemStack
    abstract fun onInteract(event: PlayerInteractEvent)

    open fun allowed(player: Player):Boolean = true

    fun isSimilar(player: Player, stack: ItemStack?): Boolean {

        if (stack == null) {
            return false
        }

        val created = this.create(player)

        if (stack.typeId != created.typeId) {
            return false
        }

        if (stack.hasItemMeta() != created.hasItemMeta()) {
            return false
        }

        if (!Practice.instance.server.itemFactory.equals(stack.itemMeta,created.itemMeta)) {
            return false
        }

        return true
    }

}