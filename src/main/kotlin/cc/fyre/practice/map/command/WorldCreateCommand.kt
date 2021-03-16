package cc.fyre.practice.map.command

import cc.fyre.practice.Practice
import net.frozenorb.qlib.command.Command
import net.frozenorb.qlib.command.Param
import org.bukkit.*
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.generator.ChunkGenerator

import org.bukkit.WorldCreator
import kotlin.random.Random


object WorldCreateCommand {

    @JvmStatic
    @Command(names = ["world create"],permission = "command.world.create")
    fun execute(sender: CommandSender,@Param(name = "world")worldName: String) {

        if(Bukkit.getWorld(worldName) != null) {
            sender.sendMessage("${ChatColor.RED}A world named $worldName exists.")
            return
        }

        val creator = WorldCreator(worldName)

        creator.generator(object : ChunkGenerator() {
            override fun generate(world: World,random: java.util.Random,x: Int,z: Int): ByteArray {
                return ByteArray(32768) //Empty byte array
            }
        })

        val world = Practice.instance.server.createWorld(creator)

        val location = Location(world,0.0,50.0,0.0)

        world.setSpawnLocation(0,50,0)

        location.block.type = Material.BEDROCK

        if(sender is Player) {
            sender.teleport(world.spawnLocation)
        }

        sender.sendMessage("${ChatColor.GOLD}Created a world with name ${ChatColor.WHITE}$worldName")
    }

}