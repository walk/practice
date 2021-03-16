package cc.fyre.practice.map.selection

import cc.fyre.practice.map.data.Map
import net.frozenorb.qlib.util.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.block.Action

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class MapClaimSelection(val map: Map) {

    var first: Location? = null
    var second: Location? = null

    var lastUpdate: Long = 0L

    constructor(map: Map, first: Location, second: Location): this(map) {
        this.first = first
        this.second = second
    }

    fun getFormattedMessage(action: Action, location: Location):Array<String?> {

        val toReturn = arrayOfNulls<String>(2)

        toReturn[0] = "${ChatColor.YELLOW}Set map's location ${ChatColor.LIGHT_PURPLE}${if (action == Action.LEFT_CLICK_BLOCK) 1 else 2}${ChatColor.YELLOW} to ${ChatColor.GREEN}(${ChatColor.WHITE}${location.blockX}, ${location.blockY}, ${location.blockZ}${ChatColor.GREEN})${ChatColor.YELLOW}."

        return toReturn
    }

    companion object {
        val ITEM = ItemBuilder.of(Material.GOLD_HOE)
            .name("${ChatColor.GREEN}Claim Map")
            .setLore(arrayListOf(
                "",
                "${ChatColor.YELLOW}Right Click ${ChatColor.GOLD}Air",
                "${ChatColor.AQUA}- ${ChatColor.WHITE}Cancel current map claim",
                "",
                "${ChatColor.YELLOW}Right/Left Click ${ChatColor.GOLD}Block",
                "${ChatColor.AQUA}- ${ChatColor.WHITE}Select map's corners",
                "",
                "${ChatColor.BLUE}Crouch ${ChatColor.YELLOW}Left Click ${ChatColor.GOLD}Air",
                "${ChatColor.AQUA}- ${ChatColor.WHITE}Purchase current map claim"
            ))
            .build()
    }

}