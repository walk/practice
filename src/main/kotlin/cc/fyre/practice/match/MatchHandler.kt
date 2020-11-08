package cc.fyre.practice.match

import cc.fyre.practice.Practice
import cc.fyre.practice.match.data.Match
import java.util.*
import kotlin.collections.HashSet

/**
 * @author Brew
 *
 * @date 11/6/2020
 * @project Practice
 */
class MatchHandler(private val instance: Practice) {

    val cache = HashSet<Match>()
    val uuidToMatch = HashMap<UUID,Match>()

    fun findMatchByUuid(id: UUID): Match? {
        return this.uuidToMatch[id]
    }

}