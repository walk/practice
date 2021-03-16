package cc.fyre.practice.queue.menu.edit

import cc.fyre.practice.Practice
import cc.fyre.practice.queue.data.Queue
import cc.fyre.practice.queue.menu.QueueEditMenu
import net.frozenorb.qlib.menu.Button
import net.frozenorb.qlib.menu.Menu
import net.frozenorb.qlib.util.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class QueueEditMapsMenu(private val queue: Queue): Menu() {

    override fun isAutoUpdate(): Boolean {
        return true
    }

    override fun getTitle(player: Player?): String {
        return "Queue Maps"
    }

    override fun getButtons(player: Player): MutableMap<Int, Button> {

        val toReturn = HashMap<Int,Button>()

        Practice.instance.mapHandler.maps.forEach {

            toReturn[toReturn.size] = object : Button() {

                override fun getName(player: Player): String {
                    return "Not Setup"
                }

                override fun getDescription(player: Player): MutableList<String> {
                    return mutableListOf()
                }

                override fun getMaterial(player: Player): Material {
                    return Material.BEDROCK
                }

                override fun getButtonItem(player: Player): ItemStack {

                    val itemBuilder = ItemBuilder.of(Material.GRASS).name("${ChatColor.RED}${it.id}")

                    if(this@QueueEditMapsMenu.queue.findMaps().contains(it)) {
                        itemBuilder.enchant(Enchantment.DURABILITY,10)
                    }

                    return itemBuilder.build()
                }

                override fun clicked(player: Player,slot: Int,clickType: ClickType) {

                    if(this@QueueEditMapsMenu.queue.findMaps().contains(it)) {
                        this@QueueEditMapsMenu.queue.maps.remove(it.id)
                    } else {
                        this@QueueEditMapsMenu.queue.maps.add(it.id)
                    }

                }

            }

        }

        return toReturn
    }

    override fun onClose(player: Player) {

        Bukkit.getServer().scheduler.runTaskLater(Practice.instance,{

            if(player.openInventory != null) {
                return@runTaskLater
            }

            QueueEditMenu(this.queue).openMenu(player)

        },3L)

    }

}