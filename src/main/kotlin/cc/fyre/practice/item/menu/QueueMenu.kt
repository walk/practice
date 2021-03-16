package cc.fyre.practice.item.menu

import cc.fyre.practice.Practice
import net.frozenorb.qlib.menu.Button
import net.frozenorb.qlib.menu.Menu
import net.frozenorb.qlib.util.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class QueueMenu(private val ranked: Boolean): Menu() {

    override fun getTitle(player: Player): String {
        return if(this.ranked) "Ranked Queue" else "Unranked Queue"
    }

    override fun getButtons(player: Player): MutableMap<Int, Button> {
        val toReturn = HashMap<Int,Button>()

        Practice.instance.queueHandler.cache.filter{it.maps.isNotEmpty()}.forEach{

            toReturn[toReturn.size] = object : Button() {

                override fun getName(player: Player): String {
                    return it.displayName
                }

                override fun getDescription(player: Player): MutableList<String> {
                    return mutableListOf()
                }

                override fun getMaterial(player: Player): Material {
                    return Material.BEDROCK
                }

                override fun getButtonItem(player: Player): ItemStack {

                    val lore = ArrayList<String>()

                    if(Practice.instance.queueHandler.isInQueue(player.uniqueId,it.matchType)) {
                        lore.add("${ChatColor.RED}Click to leave queue")
                    } else {
                        lore.add("${ChatColor.GREEN}Click to join queue")
                    }

                    return ItemBuilder.copyOf(it.matchType.display.clone()).setLore(lore).build()
                }

                override fun clicked(player: Player,slot: Int,clickType: ClickType) {

                    if(Practice.instance.queueHandler.isInQueue(player.uniqueId,it.matchType)) {
                        Practice.instance.queueHandler.removeFromQueue(player.uniqueId)
                        player.sendMessage("${ChatColor.RED}Left the ${it.displayName} ${ChatColor.RED}queue.")
                    } else {
                        Practice.instance.queueHandler.addToQueue(player.uniqueId,it,this@QueueMenu.ranked)
                        player.sendMessage("${ChatColor.GOLD}Joined the ${it.displayName} ${ChatColor.GOLD}queue.")
                    }

                }


            }

        }

        return toReturn
    }


}