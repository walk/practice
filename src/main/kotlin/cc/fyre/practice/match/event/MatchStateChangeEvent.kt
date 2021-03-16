package cc.fyre.practice.match.event

import cc.fyre.practice.match.data.Match
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author Brew
 *
 * @date 3/5/2021
 * @project practice
 */
class MatchStateChangeEvent(val previous: Match.MatchState,val new: Match.MatchState,val match: Match): Event(), Cancellable {

    private var cancelled = false

    override fun isCancelled(): Boolean {
        return this.cancelled
    }

    override fun setCancelled(cancelled: Boolean) {
        this.cancelled = cancelled
    }

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        @JvmStatic val handlerList = HandlerList()
    }

}