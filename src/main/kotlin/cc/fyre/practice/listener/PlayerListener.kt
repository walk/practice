package cc.fyre.practice.listener

import cc.fyre.carnage.menu.Menu
import cc.fyre.practice.Practice
import cc.fyre.practice.party.data.Party
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.*
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import org.bukkit.event.weather.WeatherChangeEvent

/**
 * @author Brew
 *
 * @date 11/5/2020
 * @project Practice
 */
class PlayerListener(private val instance: Practice): Listener {

    // @TODO
    // Add a check for the players state once matchmaking / states are complete
    // then run these events

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onPlayerJoin(event: PlayerJoinEvent) {
        this.instance.partyHandler.cache.add(Party(event.player.uniqueId))

        event.player.foodLevel = 20
        event.player.health = 20.0
        event.player.saturation = 20.0F
        event.player.inventory.clear()

        event.player.gameMode = GameMode.ADVENTURE

        event.player.teleport(this.instance.server.worlds[0].spawnLocation.add(0.5, 0.0, 0.5))

        this.instance.itemHandler.items.stream().forEach{event.player.inventory.setItem(it.slot(event.player),it.create(event.player))}
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onFoodLevelChange(event: FoodLevelChangeEvent) {
        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onWeatherChange(event: WeatherChangeEvent) {
        event.world.time = 6000
        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onEntityDamage(event: EntityDamageEvent) {
        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onBlockBreak(event: BlockBreakEvent) {

        if (event.player.gameMode == GameMode.CREATIVE) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onBlockPlace(event: BlockPlaceEvent) {

        if (event.player.gameMode == GameMode.CREATIVE) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onPlayerDropItem(event: PlayerDropItemEvent) {

        if (event.player.gameMode == GameMode.CREATIVE) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onPlayerPickupItem(event: PlayerPickupItemEvent) {

        if (event.player.gameMode == GameMode.CREATIVE) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onInventoryClick(event: InventoryClickEvent) {

        if (event.whoClicked !is Player) {
            return
        }

        if (event.whoClicked.gameMode == GameMode.CREATIVE) {
            return
        }

        if (event.inventory.holder is Menu) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onCreeperPower(event: CreeperPowerEvent) {
        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onEntityExplode(event: EntityExplodeEvent) {
        event.blockList().clear()
        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onExplosionPrime(event: ExplosionPrimeEvent) {
        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onPlayerMove(event: PlayerMoveEvent) {

        if (event.to.y > 50) {
            return
        }

        event.player.teleport(this.instance.server.worlds[0].spawnLocation)
    }

}