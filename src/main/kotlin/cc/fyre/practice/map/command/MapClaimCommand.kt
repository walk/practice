package cc.fyre.practice.map.command

import cc.fyre.practice.Practice
import cc.fyre.practice.map.data.Map
import net.frozenorb.qlib.command.Command
import net.frozenorb.qlib.command.Param
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.stream.IntStream

object MapClaimCommand {

    @JvmStatic
    @Command(names = ["map claim"],permission = "prison.mine.claim")
    fun execute(player: Player,@Param(name = "map")map: Map) {

        if(Practice.instance.mapHandler.findMapClaimSelection(player.uniqueId) != null) {
            player.sendMessage("${ChatColor.RED}You already have a claiming wand in your inventory!")
            return
        }

        val slot = IntStream.range(0,9).filter{player.inventory.getItem(it) == null}.findFirst()

        if (!slot.isPresent) {
            player.sendMessage("${ChatColor.RED}You don't have space in your hot bar for the claim wand!")
            return
        }

        Practice.instance.mapHandler.setMapClaimSelection(player,map,slot.asInt)
    }
}