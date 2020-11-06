package cc.fyre.practice.profile.listener

import cc.fyre.practice.Practice
import cc.fyre.practice.profile.data.Profile
import cc.fyre.venom.profile.exception.ProfileLoadException
import com.mongodb.client.model.Filters
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent

/**
 * @author Brew
 *
 * @date 11/5/2020
 * @project Practice
 */
class ProfileListener(private val instance: Practice) : Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    private fun onPreLogin(event: AsyncPlayerPreLoginEvent) {
        val document = this.instance.profileHandler.collection.find(Filters.eq("_id",event.uniqueId.toString())).first()

        val profile: Profile = if (document == null) Profile(event.uniqueId) else {
            try {
                Practice.GSON.fromJson(document.toJson(),Profile::class.java)
            } catch (ex: Exception) {
                event.loginResult = AsyncPlayerPreLoginEvent.Result.KICK_OTHER
                event.kickMessage = "${ChatColor.RED}There was an issue contacting the API, please try again later."
                throw ProfileLoadException(event.uniqueId,ex.message!!)
            }
        }
    }

}