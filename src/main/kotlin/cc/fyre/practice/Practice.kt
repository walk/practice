package cc.fyre.practice

import cc.fyre.carnage.Carnage
import cc.fyre.carnage.nametag.data.adapter.NameTagAdapter
import cc.fyre.practice.database.DatabaseHandler
import cc.fyre.practice.item.ItemHandler
import cc.fyre.practice.listener.PlayerListener
import cc.fyre.practice.nametag.NameTagProvider
import cc.fyre.practice.party.PartyHandler
import cc.fyre.practice.profile.ProfileHandler
import cc.fyre.practice.scoreboard.PracticeScoreboardAdapter
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy

import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Brew
 *
 * @date 9/22/2020
 * @project Practice
 */
class Practice : JavaPlugin() {

    lateinit var databaseHandler: DatabaseHandler

    lateinit var profileHandler: ProfileHandler
    lateinit var partyHandler: PartyHandler

    lateinit var itemHandler: ItemHandler

    override fun onEnable() {
        instance = this

        this.saveDefaultConfig()

        this.databaseHandler = DatabaseHandler(this)

        this.profileHandler = ProfileHandler(this)
        this.partyHandler = PartyHandler(this)

        this.itemHandler = ItemHandler(this)

        Carnage.instance.scoreboardHandler.adapter = PracticeScoreboardAdapter(this)
        Carnage.instance.nameTagHandler.adapters.add(NameTagProvider(this))

        this.server.pluginManager.registerEvents(PlayerListener(this), this)
    }

    companion object {
        lateinit var instance: Practice

        val GSON = GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create()
    }
}