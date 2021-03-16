package cc.fyre.practice.map.command.parameter

import cc.fyre.practice.Practice
import cc.fyre.practice.map.data.Map
import net.frozenorb.qlib.command.ParameterType
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class MapParameterType(private val instance: Practice): ParameterType<Map?> {

    override fun transform(sender: CommandSender,input: String): Map? {

        val map = this.instance.mapHandler.findById(input)

        if(map == null) {
            sender.sendMessage("${ChatColor.RED}Can't find a map with the name $input.")
            return null
        }

        return map
    }

    override fun tabComplete(p0: Player?, p1: MutableSet<String>?, p2: String?): MutableList<String> {
        return mutableListOf()
    }

}