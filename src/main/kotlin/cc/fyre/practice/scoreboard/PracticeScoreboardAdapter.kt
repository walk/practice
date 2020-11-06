package cc.fyre.practice.scoreboard

import cc.fyre.carnage.Carnage
import cc.fyre.carnage.scoreboard.data.adapter.ScoreboardAdapter
import cc.fyre.carnage.util.TimeUtil
import cc.fyre.practice.Practice
import net.minecraft.util.org.apache.commons.lang3.StringUtils
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit

/**
 * @author Brew
 *
 * @date 9/22/2020
 * @project Practice
 */
class PracticeScoreboardAdapter(private val instance: Practice) : ScoreboardAdapter {

    private var dots = 1

    init {
        this.instance.server.scheduler.runTaskTimer(this.instance,{ if (this.dots++ >= 3) this.dots = 0 },12L,12L)
    }

    companion object {

        val LINE = "${org.bukkit.ChatColor.GRAY}${org.bukkit.ChatColor.STRIKETHROUGH}${StringUtils.repeat("-",20)}"

    }

    override fun getTitle(player: Player): String {

        var suffix = "${org.bukkit.ChatColor.DARK_RED}Practice"

        val reboot = Carnage.instance.rebootHandler.findRemaining()

        if (Carnage.instance.rebootHandler.isActive() && reboot > 0L && reboot <= TimeUnit.MINUTES.toMillis(5L)) {
            suffix = "${org.bukkit.ChatColor.RED}${TimeUtil.formatIntoMMSS(reboot)}"
        }

        return "${org.bukkit.ChatColor.DARK_RED}${org.bukkit.ChatColor.BOLD} CavePvP ${org.bukkit.ChatColor.GRAY}┃ $suffix"
    }

    override fun getScores(player: Player): List<String> {

        val toReturn = ArrayList<String>()

        toReturn.add(LINE)

        val party = this.instance.partyHandler.findById(player.uniqueId).get()

        toReturn.add("${org.bukkit.ChatColor.WHITE}Online${org.bukkit.ChatColor.GRAY}: ${org.bukkit.ChatColor.RED}${this.instance.server.onlinePlayers.size}")
        toReturn.add("${org.bukkit.ChatColor.WHITE}In Fights${org.bukkit.ChatColor.GRAY}: ${org.bukkit.ChatColor.RED}0")
        toReturn.add("${org.bukkit.ChatColor.WHITE}In Queue${org.bukkit.ChatColor.GRAY}: ${org.bukkit.ChatColor.RED}0")

        if (party.members.size != 1) {
            toReturn.add(LINE)
            toReturn.add("${org.bukkit.ChatColor.BLUE}${org.bukkit.ChatColor.BOLD}Party${org.bukkit.ChatColor.GRAY}: ${org.bukkit.ChatColor.WHITE}${party.members.size}")
        }

        toReturn.add(" ")
        toReturn.add("${ChatColor.DARK_RED}cavepvp.org")
        toReturn.add(LINE)

        return toReturn
    }

    override fun getInterval(): Long {
        return 2L
    }

}