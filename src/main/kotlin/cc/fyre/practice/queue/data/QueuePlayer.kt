package cc.fyre.practice.queue.data

import net.frozenorb.qlib.util.TimeUtils
import java.util.*

/**
 * @author Brew
 *
 * @date 3/5/2021
 * @project practice
 */
class QueuePlayer(val id: UUID) {

    var queue: Queue? = null

    var ranked = false

    val joined = System.currentTimeMillis()

    fun findWaitTime(): Int {
        return TimeUtils.getSecondsBetween(Date(this.joined),Date(System.currentTimeMillis()))
    }

}