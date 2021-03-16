package cc.fyre.practice.map.listener

import cc.fyre.practice.Practice
import cc.fyre.practice.map.selection.MapClaimSelection
import net.frozenorb.qlib.cuboid.Cuboid
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerQuitEvent

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class MapClaimListener(private val instance: Practice): Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onPlayerInteract(event: PlayerInteractEvent) {

        if(event.item == null) {
            return
        }

        if(!event.item.isSimilar(MapClaimSelection.ITEM)) {
            return
        }

        val selection = this.instance.mapHandler.findMapClaimSelection(event.player.uniqueId) ?: return

        if (event.action == Action.LEFT_CLICK_AIR) {

            if (!event.player.isSneaking) {
                return
            }

            if (selection.first == null || selection.second == null) {
                event.player.sendMessage("${ChatColor.RED}You have not selected both corners of your claim yet!")
                return
            }

            val cuboid = Cuboid(selection.first,selection.second)

            selection.map.cuboid = cuboid

            event.player.sendMessage("${ChatColor.YELLOW}You have created a cuboid for ${ChatColor.RED}${selection.map.id} map")

            event.player.itemInHand = null

            this.instance.mapHandler.mapSelections.remove(event.player.uniqueId)

            Bukkit.getServer().scheduler.runTaskAsynchronously(Practice.instance) {
                this.instance.mapHandler.update(selection.map)
            }

        }

        if (event.clickedBlock == null) {
            return
        }

        if (selection.lastUpdate != 0L && (System.currentTimeMillis() - selection.lastUpdate) <= 200L) {
            return
        }

        if (event.action == Action.LEFT_CLICK_BLOCK) {
            selection.first = event.clickedBlock.location
        } else {
            selection.second = event.clickedBlock.location
        }

        selection.lastUpdate = System.currentTimeMillis()
        selection.getFormattedMessage(event.action,event.clickedBlock.location).filterNotNull().forEach{event.player.sendMessage(it)}

        val locations = ArrayList<Location>()

        for (i in event.clickedBlock.location.blockY..event.clickedBlock.world.maxHeight) {
            locations.add(Location(event.clickedBlock.world,event.clickedBlock.location.x,i.toDouble(),event.clickedBlock.location.z))
        }

    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onPlayerQuit(event: PlayerQuitEvent) {

        if(event.player == null) {
            return
        }

        if(!this.instance.mapHandler.mapSelections.containsKey(event.player.uniqueId)) return

        this.instance.mapHandler.mapSelections.remove(event.player.uniqueId)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onPlayerDropItem(event: PlayerDropItemEvent) {

        if(!event.itemDrop.itemStack.isSimilar(MapClaimSelection.ITEM)) {
           return
        }

        if(!this.instance.mapHandler.mapSelections.containsKey(event.player.uniqueId)) {
            return
        }

        this.instance.mapHandler.mapSelections.remove(event.player.uniqueId)
    }

}