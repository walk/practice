package cc.fyre.practice.match.data

import cc.fyre.practice.Practice
import cc.fyre.practice.map.data.Map
import cc.fyre.practice.match.data.team.MatchTeam
import cc.fyre.practice.match.event.MatchStateChangeEvent
import com.google.gson.annotations.SerializedName
import net.frozenorb.qlib.util.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionType
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * @author Brew
 *
 * @date 11/9/2020
 * @project practice
 */
class Match(@SerializedName("_id")val id: UUID,val teams: ArrayList<MatchTeam>,val matchType: MatchType) {

    var map: Map? = null

    var state = MatchState.STARTING

    var isRanked: Boolean = false

    var world = ""

    fun changeState(state: MatchState) {
        Practice.instance.server.pluginManager.callEvent(MatchStateChangeEvent(this.state,state,this))

        this.state = state
    }

    fun findPlayers(): MutableSet<UUID> {
        val toReturn = HashSet<UUID>()

        this.teams.forEach{it.members.forEach{member -> toReturn.add(member.key)}}

        return toReturn
    }

    fun findTeamOne(): MatchTeam {
        return this.teams[0]
    }

    fun findTeamTwo(): MatchTeam {
        return this.teams[1]
    }

    enum class MatchType(val displayName: String,val queueName: String,val display: ItemStack) {

        NODEBUFF("${ChatColor.RED}No Debuff","nodebuff",ItemBuilder.of(Material.POTION).data(PotionType.INSTANT_HEAL.damageValue.toShort()).name("${ChatColor.RED}No Debuff").build()),
        DEBUFF("${ChatColor.GREEN}Debuff","debuff",ItemBuilder.of(Material.POTION).data(PotionType.POISON.damageValue.toShort()).name("${ChatColor.RED}No Debuff").build())

    }

    enum class MatchState {
        STARTING,
        ACTIVE,
        ENDING,
        FINISHED
    }

}