package cc.fyre.practice.item.listener

import cc.fyre.practice.Practice
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

/**
 * @author Brew
 *
 * @date 11/6/2020
 * @project Practice
 */
class ItemListener(private val instance: Practice): Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onPlayerInteract(event: PlayerInteractEvent) {

        if (!event.action.name.contains("RIGHT",true)) {
            return
        }

        if (event.item == null) {
            return
        }

        val item = this.instance.itemHandler.findItemByItemStack(event.item,event.player)

        if(item == null) {
            return
        }

        item.onInteract(event)
    }

}