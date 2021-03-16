package cc.fyre.practice.map.menu.button

import cc.fyre.practice.map.data.Map
import net.frozenorb.qlib.menu.Button
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class MapEditButton(private val map: Map): Button() {

    override fun getName(player: Player): String {
        return "${ChatColor.RED}${this.map.id}"
    }

    override fun getDescription(player: Player): MutableList<String> {
        val toReturn = ArrayList<String>()

        return toReturn
    }

    override fun getMaterial(player: Player): Material {
        return Material.GRASS
    }



}