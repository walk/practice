package cc.fyre.practice.match.listener

import cc.fyre.practice.Practice
import cc.fyre.practice.match.data.Match
import cc.fyre.practice.match.event.MatchStateChangeEvent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

/**
 * @author Brew
 *
 * @date 3/5/2021
 * @project practice
 */
class MatchListener(private val instance: Practice): Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    private fun onMatchChange(event: MatchStateChangeEvent) {

        if(event.new == Match.MatchState.STARTING) {

            if(event.match.map == null) return

            val mapWorld = this.instance.mapHandler.loadMap(event.match.map!!)

            println(mapWorld.name)

            val teamOneSpawn = Location(mapWorld,event.match.map!!.teamOneSpawn?.x!!,event.match.map!!.teamOneSpawn?.y!!,event.match.map!!.teamOneSpawn?.z!!)
            val teamTwoSpawn = Location(mapWorld,event.match.map!!.teamTwoSpawn?.x!!,event.match.map!!.teamTwoSpawn?.y!!,event.match.map!!.teamTwoSpawn?.z!!)

            event.match.findTeamOne().findAlive().map{Bukkit.getPlayer(it)}.forEach{it.teleport(teamOneSpawn)}
            event.match.findTeamTwo().findAlive().map{Bukkit.getPlayer(it)}.forEach{it.teleport(teamTwoSpawn)}

            return
        }

    }

}