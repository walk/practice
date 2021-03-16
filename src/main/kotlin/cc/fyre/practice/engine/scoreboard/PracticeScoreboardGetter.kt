package cc.fyre.practice.engine.scoreboard

import cc.fyre.practice.Practice
import net.frozenorb.qlib.qLib
import net.frozenorb.qlib.scoreboard.ScoreGetter
import net.frozenorb.qlib.util.LinkedList
import net.frozenorb.qlib.util.TimeUtils
import net.minecraft.util.org.apache.commons.lang3.StringUtils
import org.bukkit.ChatColor
import org.bukkit.entity.Player

/**
 * @author Brew
 *
 * @date 9/22/2020
 * @project Practice
 */
class PracticeScoreboardGetter(private val instance: Practice) : ScoreGetter {

    private var dots = 1

    init {
        this.instance.server.scheduler.runTaskTimer(this.instance,{ if (this.dots++ >= 3) this.dots = 0 },12L,12L)
    }

    override fun getScores(scores: LinkedList<String>, player: Player) {

        scores.addFirst("&8&7&m--------------------")

        this.instance.config.getStringList("scoreboard.main").forEach {
            scores.add(it
                .replace("{online}",this.instance.server.onlinePlayers.size.toString())
                .replace("{inFights}",this.instance.matchHandler.currentMatches.size.toString())
                .replace("{inQueue}",this.instance.queueHandler.queue.size.toString())
            )
        }

        val party = this.instance.partyHandler.findById(player.uniqueId)

        if(party.members.size > 1) {
            this.instance.config.getStringList("scoreboard.party").forEach {
                scores.add(it
                    .replace("{party}","${party.findMembers().size}")
                )
            }
        }

        if(this.instance.queueHandler.isInQueue(player.uniqueId)) {

            val queue = this.instance.queueHandler.findQueue(player.uniqueId)

            if(queue == null) return

            scores.add("&5&7&m--------------------")

            this.instance.config.getStringList("scoreboard.queue").forEach {
                scores.add(it
                    .replace("{type}",queue.queue!!.displayName)
                    .replace("{gameType}",if(queue.ranked) "Ranked" else "Unranked")
                    .replace("{time}",TimeUtils.formatIntoMMSS(queue.findWaitTime()))
                )
            }

        }

        scores.add("&f&7&m--------------------")


    }

    companion object {

        val LINE = "&9&7&m--------------------"

    }

}