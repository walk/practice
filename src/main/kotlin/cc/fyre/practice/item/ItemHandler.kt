package cc.fyre.practice.item

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import cc.fyre.practice.Practice
import cc.fyre.practice.item.data.Item
import cc.fyre.practice.item.data.type.*
import cc.fyre.practice.item.listener.ItemListener
import java.util.*
import kotlin.collections.HashSet

/**
 * @author Brew
 *
 * @date 11/6/2020
 * @project Practice
 */
class ItemHandler(private val instance: Practice) {

    val items = HashSet<Item>()

    init {
        this.items.add(UnrankedItem(this.instance))
        this.items.add(RankedItem(this.instance))
        this.items.add(SpectateItem(this.instance))
        this.items.add(JoinEventItem(this.instance))
        this.items.add(KitEditorItem(this.instance))

        this.instance.server.pluginManager.registerEvents(ItemListener(this.instance),this.instance)
    }

    fun findItemByItemStack(itemStack: ItemStack, player: Player): Item? {
        return this.items.firstOrNull{it.isSimilar(player,itemStack)}
    }
}