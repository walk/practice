package cc.fyre.practice.map

import cc.fyre.practice.Practice
import cc.fyre.practice.database.DatabaseHandler
import cc.fyre.practice.map.command.*
import cc.fyre.practice.map.command.parameter.MapParameterType
import cc.fyre.practice.map.data.Map
import cc.fyre.practice.map.listener.MapClaimListener
import cc.fyre.practice.map.selection.MapClaimSelection
import com.google.gson.JsonSyntaxException
import com.mongodb.client.model.Filters
import net.frozenorb.qlib.command.FrozenCommandHandler
import net.minecraft.util.org.apache.commons.io.FileUtils
import org.bson.Document
import org.bukkit.ChatColor
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.entity.Player
import java.io.File
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * @author Brew
 *
 * @date 11/6/2020
 * @project Practice
 */
class MapHandler(private val instance: Practice) {

    val maps = HashSet<Map>()
    val activeMaps = HashMap<String, Map>()

    val mapSelections = HashMap<UUID,MapClaimSelection>()

    val container = File("/home/practice/maps")
    val collection = this.instance.databaseHandler.mongoDB.getCollection("maps")

    init {

        if(!this.container.exists()) {
            this.container.mkdirs()
        }

        for(file in this.container.listFiles()?.filterNotNull()?.filter{it.isDirectory}!!) {

            val map: Map
            val document = this.collection.find(Filters.eq("_id",file.name)).first()

            if(document == null) {

                map = Map(file.name)

                if(!this.update(map)) {
                    Practice.instance.logger.warning("Failed to crate map ${file.name}")
                    continue
                }

                this.maps.add(map)
                Practice.instance.logger.info("Loaded map ${map.id}")
                continue
            }

            try {
                map = Practice.instance.gson.fromJson(document.toJson(),Map::class.java)
            } catch (ex: JsonSyntaxException) {
                Practice.instance.logger.warning("Failed to load map ${file.name}")
                continue
            }

            this.maps.add(map)
            Practice.instance.logger.info("Loaded map ${map.id}")
        }

        this.instance.server.pluginManager.registerEvents(MapClaimListener(this.instance),this.instance)

        FrozenCommandHandler.registerParameterType(Map::class.java,MapParameterType(this.instance))

        FrozenCommandHandler.registerClass(MapClaimCommand::class.java)
        FrozenCommandHandler.registerClass(MapEditorCommand::class.java)
        FrozenCommandHandler.registerClass(MapSaveCommand::class.java)
        FrozenCommandHandler.registerClass(MapSetSpawnCommand::class.java)
        FrozenCommandHandler.registerClass(WorldCreateCommand::class.java)
    }

    fun dispose() {
        this.activeMaps.mapNotNull{this.instance.server.getWorld(it.key)}.forEach{this.deleteMap(it)}
    }

    fun findById(id: String): Map? {
        return this.maps.firstOrNull{it.id.equals(id,true)}
    }

    fun update(map: Map): Boolean {
        return this.collection.updateOne(Filters.eq("_id",map.id), Document("\$set", Document.parse(Practice.instance.gson.toJson(map))),DatabaseHandler.UPDATE_OPTIONS).wasAcknowledged()
    }

    fun loadMap(map: Map): World {
        val id = findNextId()

        FileUtils.copyDirectory(map.getContainer(),File("${this.instance.server.worldContainer.path}/${map.id}${id}"))

        val world = this.instance.server.createWorld(WorldCreator.name("${map.id}${id}"))

//        this.instance.server.pluginManager.callEvent(ArenaLoadEvent(arena,world))

        return world
    }

    fun deleteMap(world: World) {

        this.instance.server.unloadWorld(world,true)
        this.instance.server.worlds.remove(world)

        FileUtils.deleteDirectory(world.worldFolder)
    }

    fun findMapClaimSelection(uuid: UUID): MapClaimSelection? {
        return this.mapSelections[uuid]
    }

    fun setMapClaimSelection(player: Player, map: Map, slot: Int) {

        if (this.mapSelections.remove(player.uniqueId) != null) {
            player.inventory.removeItem(MapClaimSelection.ITEM)
        }

        player.inventory.setItem(slot,MapClaimSelection.ITEM)

        player.sendMessage("${ChatColor.GREEN}Gave you a claiming wand.")

        this.mapSelections[player.uniqueId] = MapClaimSelection(map)
    }

    companion object {

        var CURRENT_ID = 5

        fun findNextId(): Int {
            val newId = this.CURRENT_ID + 1

            this.CURRENT_ID = newId

            return newId
        }

    }

}