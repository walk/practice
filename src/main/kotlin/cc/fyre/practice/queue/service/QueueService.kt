package cc.fyre.practice.queue.service

import cc.fyre.practice.Practice
import cc.fyre.practice.match.data.Match
import cc.fyre.practice.match.data.team.MatchTeam
import cc.fyre.practice.queue.data.Queue
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.lang.IllegalStateException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @author Brew
 *
 * @date 3/5/2021
 * @project practice
 */
class QueueService(private val instance: Practice): BukkitRunnable() {

    override fun run() {

        try {
            this.sendUnrankedQueue()
        } catch (ex: IllegalStateException) {
            ex.printStackTrace()
        }

    }

    // @TODO
    // this is god awful find a better way to do this

    @Throws(IllegalStateException::class)
    fun sendUnrankedQueue() {

        if(this.instance.queueHandler.queue.size < 2) {
            return
        }

        val matchTeamOneMap = HashMap<UUID,MatchTeam.MemberStatus>()
        val matchTeamTwoMap = HashMap<UUID,MatchTeam.MemberStatus>()

        var queue: Queue? = null

        for(it in this.instance.queueHandler.queue.entries.filter{!it.value.ranked}.take(2).withIndex()) {

            val player = Bukkit.getPlayer(it.value.key)

            if(player == null) {
                throw IllegalStateException("Player was somehow null in queue, please report this to a developer.")
            }

            if(it.index == 0) {
                matchTeamOneMap[player.uniqueId] = MatchTeam.MemberStatus.ALIVE
            } else {
                matchTeamTwoMap[player.uniqueId] = MatchTeam.MemberStatus.ALIVE
            }

            queue = it.value.value.queue!!
            this.instance.queueHandler.removeFromQueue(it.value.key)
        }

        if (queue == null) {
            throw IllegalStateException("Service Queue was null when trying to create match")
        }

        val matchTeamOne = MatchTeam(matchTeamOneMap)
        val matchTeamTwo = MatchTeam(matchTeamTwoMap)

        val teams = ArrayList<MatchTeam>()
        teams.add(matchTeamOne)
        teams.add(matchTeamTwo)

        val map = queue.findMaps().random()

        val match = Match(UUID.randomUUID(),teams,queue.matchType)

        match.map = map
        match.isRanked = false

        Bukkit.getServer().scheduler.callSyncMethod()

        match.findPlayers().map{Bukkit.getPlayer(it)}.forEach{it.sendMessage("${ChatColor.GOLD}Loaded into map ${ChatColor.WHITE}${match.map!!.id} ${ChatColor.GOLD}in gamemode ${match.matchType.displayName}")}
    }

}