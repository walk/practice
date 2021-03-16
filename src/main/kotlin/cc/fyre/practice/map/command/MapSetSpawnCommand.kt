package cc.fyre.practice.map.command

import cc.fyre.practice.Practice
import cc.fyre.practice.map.data.Map
import net.frozenorb.qlib.command.Command
import net.frozenorb.qlib.command.Param
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object MapSetSpawnCommand {

    @JvmStatic
    @Command(names = ["map setspawn"],permission = "command.map.setspawn")
    fun execute(player: Player, @Param(name = "map")map: Map, @Param(name = "spawn")spawn: String) {

        when(spawn) {

            "one" -> {
                map.teamOneSpawn = player.location.clone().add(0.5,0.0,0.5)
            }

            "two" -> {
                map.teamTwoSpawn = player.location.clone().add(0.5,0.0,0.5)
            }

            else -> {
                player.sendMessage("${ChatColor.RED}Use \"one\" or \"two\" to define a spawn point.")
            }

        }

        Bukkit.getServer().scheduler.runTaskAsynchronously(Practice.instance) {
            Practice.instance.mapHandler.update(map)
        }

    }

}