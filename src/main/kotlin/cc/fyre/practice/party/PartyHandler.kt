package cc.fyre.practice.party

import cc.fyre.practice.Practice
import cc.fyre.practice.party.data.Party
import java.util.*
import kotlin.collections.HashSet

/**
 * @author Brew
 *
 * @date 11/5/2020
 * @project Practice
 */
class PartyHandler(private val instance: Practice) {

    val cache = HashSet<Party>()

    init {



    }

    fun findById(id: UUID): Party {
        return this.cache.stream().filter{it.members.contains(id)}.findFirst().orElse(Party(id))
    }

}