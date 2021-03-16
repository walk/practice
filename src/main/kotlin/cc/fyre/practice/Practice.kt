package cc.fyre.practice

import cc.fyre.practice.database.DatabaseHandler
import cc.fyre.practice.database.json.ItemStackJsonAdapter
import cc.fyre.practice.database.json.LocationJsonAdapter
import cc.fyre.practice.engine.EngineHandler
import cc.fyre.practice.item.ItemHandler
import cc.fyre.practice.listener.PlayerListener
import cc.fyre.practice.party.PartyHandler
import cc.fyre.practice.profile.ProfileHandler
import cc.fyre.practice.queue.QueueHandler
import cc.fyre.practice.kit.KitHandler
import cc.fyre.practice.map.MapHandler
import cc.fyre.practice.match.MatchHandler
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import org.bukkit.Location
import org.bukkit.inventory.ItemStack

import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Brew
 *
 * @date 9/22/2020
 * @project Practice
 */
class Practice : JavaPlugin() {

    val gson: Gson = GsonBuilder()
        .registerTypeAdapter(Location::class.java, LocationJsonAdapter())
        .registerTypeHierarchyAdapter(ItemStack::class.java, ItemStackJsonAdapter())
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .setPrettyPrinting().create()

    lateinit var databaseHandler: DatabaseHandler

    lateinit var engineHandler: EngineHandler

    lateinit var mapHandler: MapHandler

    lateinit var profileHandler: ProfileHandler
    lateinit var partyHandler: PartyHandler

    lateinit var queueHandler: QueueHandler
    lateinit var matchHandler: MatchHandler

    lateinit var kitHandler: KitHandler

    lateinit var itemHandler: ItemHandler

    override fun onEnable() {
        instance = this

        this.saveDefaultConfig()

        this.databaseHandler = DatabaseHandler(this)

        this.engineHandler = EngineHandler(this)

        this.mapHandler = MapHandler(this)

        this.profileHandler = ProfileHandler(this)
        this.partyHandler = PartyHandler(this)

        this.queueHandler = QueueHandler(this)
        this.matchHandler = MatchHandler(this)

        this.kitHandler = KitHandler(this)

        this.itemHandler = ItemHandler(this)

        this.server.pluginManager.registerEvents(PlayerListener(this), this)
    }

    override fun onDisable() {
        this.mapHandler.dispose()
        this.queueHandler.dispose()
        this.databaseHandler.dispose()
    }

    companion object {
        lateinit var instance: Practice
    }
}