package cc.fyre.practice.queue.data

import cc.fyre.practice.Practice
import cc.fyre.practice.kit.data.Kit
import cc.fyre.practice.match.data.Match
import com.google.gson.annotations.SerializedName
import org.bukkit.Material
import cc.fyre.practice.map.data.Map

/**
 * @author Brew
 *
 * @date 11/9/2020
 * @project practice
 */
class Queue(@SerializedName("_id")val id: String) {

    var matchType = Match.MatchType.NODEBUFF

    val maps = HashSet<String>()

    val icon: Material = Material.DIAMOND_SWORD
    val displayName = this.matchType.displayName

    val kit: String? = null

    fun findKit(): Kit? {

        if (this.kit == null) {
            return null
        }

        return Practice.instance.kitHandler.findById(this.kit)
    }

    fun findMaps(): MutableSet<Map> {
        val toReturn = HashSet<Map>()

        this.maps.forEach {
            val map = Practice.instance.mapHandler.findById(it)

            if(map == null) {
                return@forEach
            }

            toReturn.add(map)
        }

        return toReturn
    }

}