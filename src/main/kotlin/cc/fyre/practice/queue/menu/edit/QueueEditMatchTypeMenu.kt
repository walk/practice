package cc.fyre.practice.queue.menu.edit

import cc.fyre.practice.Practice
import cc.fyre.practice.match.data.Match
import cc.fyre.practice.queue.data.Queue
import cc.fyre.practice.queue.menu.QueueEditMenu
import net.frozenorb.qlib.menu.Button
import net.frozenorb.qlib.menu.Menu
import net.frozenorb.qlib.util.ItemBuilder
import org.bukkit.Bukkit
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
class QueueEditMatchTypeMenu(private val queue: Queue): Menu() {

    override fun getTitle(player: Player): String {
        return "Queue Match Types"
    }

    override fun getButtons(player: Player): MutableMap<Int, Button> {
        val toReturn = HashMap<Int,Button>()

        Match.MatchType.values().forEach {

            toReturn[toReturn.size] = object : Button() {

                override fun getName(player: Player): String {
                    return it.displayName
                }

                override fun getDescription(player: Player): MutableList<String> {
                    return mutableListOf()
                }

                override fun getMaterial(player: Player): Material {
                    return Material.POTION
                }

                override fun getButtonItem(player: Player): ItemStack {

                    val itemStack = ItemBuilder.copyOf(it.display.clone())

                    val lore = ArrayList<String>()

                    lore.add("${ChatColor.GRAY}Enabled: ${ChatColor.WHITE}${if(this@QueueEditMatchTypeMenu.queue.matchType == it) "True" else "False"}")

                    itemStack.setLore(lore)

                    return itemStack.build()
                }

                override fun clicked(player: Player,slot: Int,clickType: ClickType) {
                    this@QueueEditMatchTypeMenu.queue.matchType = it

                    QueueEditMenu(this@QueueEditMatchTypeMenu.queue).openMenu(player)
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